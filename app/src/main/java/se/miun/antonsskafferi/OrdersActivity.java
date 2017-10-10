package se.miun.antonsskafferi;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity extends AppCompatActivity {

    private TableOrdersAdapter adapter;
    private ArrayList<Order.OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        getSupportActionBar()
            .setTitle("Bord " + getIntent()
                .getIntExtra("table_number", 44));

        orderItems = new ArrayList<Order.OrderItem>();
        adapter = new TableOrdersAdapter(this, orderItems);
        ((ListView) findViewById(R.id.orderList)).setAdapter(adapter);

//        orderItems.add(new Order.OrderItem("Gino", 3));
//        orderItems.add(new Order.OrderItem("Känguru", "Utan lök"));
//        orderItems.add(new Order.OrderItem("Cola", 1));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService orderService = retrofit.create(OrderService.class);

        Call<List<OrderServiceItem>> call = orderService.getActiveOrders();

        call.enqueue(new Callback<List<OrderServiceItem>>() {
            @Override
            public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {

                if (response == null) {
                    orderItems.clear();
                    adapter.notifyDataSetChanged();
                    return;
                }

                for (OrderServiceItem item : response.body()) {
                    if ("".equals(item.getModification())) {
                        orderItems.add(new Order.OrderItem("" + item.getFoodId(), 1));
                    } else {
                        orderItems.add(new Order.OrderItem("" + item.getFoodId(), item.getModification()));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                orderItems.add(new Order.OrderItem("Fuck", 1));
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
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
