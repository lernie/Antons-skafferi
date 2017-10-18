package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
  
    private Retrofit retrofit;
    private OrderService orderService;
    private HashMap<Course, Integer> specItemIds;

    private OrderConfirmPopup orderConfirmPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        OrderStatusCache.getInstance().update(null);

        tableNumber = getIntent()
                .getIntExtra("table_number", -1);

        getSupportActionBar()
            .setTitle("Bord " + tableNumber);

        specItemIds = new HashMap<Course, Integer>();
        orderItems = new ArrayList<Order.OrderItem>();
        adapter = new TableOrdersAdapter(this, orderItems);

        ((ListView) findViewById(R.id.order_list)).setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();

        orderService = retrofit.create(OrderService.class);

        final Call<List<OrderServiceItem>> call = orderService.getOrders(tableNumber, 0);

        final CoursesCache cache = CoursesCache.getInstance();

        cache.update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                call.enqueue(new Callback<List<OrderServiceItem>>() {
                    @Override
                    public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {

                        if (response == null || response.body() == null) {
                            orderItems.clear();
                            adapter.notifyDataSetChanged();
                            return;
                        }

                        HashMap<Integer, Integer> nonSpecCount = new HashMap<Integer, Integer>();

                        for (OrderServiceItem item : response.body()) {
                            Course course = cache.getCourses().get(item.getFoodId());

                            if (item.isSpecial()) {
                                orderItems.add(new Order.OrderItem(course, item.getModification()));
                                specItemIds.put(course, item.orderId);
                            } else {
                                if (nonSpecCount.containsKey(item.getFoodId())) {
                                    nonSpecCount.put(item.getFoodId(), nonSpecCount.get(item.getFoodId()) + 1);
                                } else {
                                    nonSpecCount.put(item.getFoodId(), 1);
                                }
                            }
                        }

                        for (int key : nonSpecCount.keySet()) {
                            if (nonSpecCount.containsKey(key)) {
                                orderItems.add(new Order.OrderItem(cache.getCourses().get(key),
                                        nonSpecCount.get(key)));
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                        Toast.makeText(
                                OrdersActivity.this,
                                "Kunde inte hämta beställningar",
                                Toast.LENGTH_SHORT
                        ).show();

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra("items", (Serializable) orderItems);
        intent.putExtra("table_id", tableNumber);
        startActivity(intent);
    }

    public void clearOrders(View view){
        adapter.clear();

        orderConfirmPopup.remove();
        onBackPressed();
        /*Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);*/
    }

    public void showConfirmPopup(View view){
        orderConfirmPopup = new OrderConfirmPopup(this);
    }

    public void dissmissConfirmPopup(View view){
        orderConfirmPopup.remove();
    }

    public void removeSpecOrder(View view) {
        ListView listView = (ListView) findViewById(R.id.order_list);
        final int i = listView.getPositionForView((View) view.getParent());

        Order.OrderItem item = orderItems.get(i);

        Call<Void> call = orderService
                .updateOrder(
                        specItemIds.get(item.getCourse()),
                        new OrderService.OrderUpdate(
                            OrderStatusCache.getInstance().getIds().get("cancelled").intValue()));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() != 200) {
                    Toast.makeText(OrdersActivity.this, "Kunde inte ta bort spec-beställning, kod " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                orderItems.remove(i);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(OrdersActivity.this, "Kunde inte ta bort spec-beställning", Toast.LENGTH_LONG).show();
            }
        });
    }
}
