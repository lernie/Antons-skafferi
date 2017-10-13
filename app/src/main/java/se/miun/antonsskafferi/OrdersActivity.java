package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity extends BackButtonActivity {

    private TableOrdersAdapter adapter;
    private ArrayList<Order.OrderItem> orderItems;
    private int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        tableNumber = getIntent()
                .getIntExtra("table_number", -1);

        getSupportActionBar()
            .setTitle("Bord " + tableNumber);

        orderItems = new ArrayList<Order.OrderItem>();
        adapter = new TableOrdersAdapter(this, orderItems);
        ((ListView) findViewById(R.id.orderList)).setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService orderService = retrofit.create(OrderService.class);

        final Call<List<OrderServiceItem>> call = orderService.getOrders(tableNumber, 0);

        final CoursesCache cache = CoursesCache.getInstance();
        cache.update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                call.enqueue(new Callback<List<OrderServiceItem>>() {
                    @Override
                    public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {

                        if (response == null) {
                            orderItems.clear();
                            adapter.notifyDataSetChanged();
                            return;
                        }

                        for (OrderServiceItem item : response.body()) {
                            String name = cache.getCourses().get(item.getFoodId()).getName();

                            if (item.isSpecial()) {
                                orderItems.add(new Order.OrderItem(name, item.getModification()));
                            } else {
                                orderItems.add(new Order.OrderItem(name, 1));
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                        orderItems.add(new Order.OrderItem("Fuck", 1)); // TODO: Remove this lol
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra("items", (Serializable) orderItems);
        startActivity(intent);
    }

    public void clearOrders(View view){
        adapter.clear();
    }

    public void clearSpec(View view){
        ListView listView = (ListView) findViewById(R.id.orderList);
        int i = listView.getPositionForView((View) view.getParent());
        orderItems.remove(i);
        adapter.notifyDataSetChanged();
    }

}
