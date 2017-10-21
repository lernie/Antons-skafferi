package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
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
    private ArrayList<Integer> orderIds;
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

        // Activity was restarted
        if (savedInstanceState != null) {
            tableNumber = savedInstanceState.getInt("table_number", -1);
        } else {
            tableNumber = getIntent()
                .getIntExtra("table_number", -2);
        }

        if (tableNumber > 0) {
            getSupportActionBar()
                .setTitle("Bord " + tableNumber);
        }

        orderIds = new ArrayList<Integer>();
        specItemIds = new HashMap<Course, Integer>();
        orderItems = new ArrayList<Order.OrderItem>();
        adapter = new TableOrdersAdapter(this, orderItems);

        ((ListView) findViewById(R.id.order_list)).setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        orderService = retrofit.create(OrderService.class);
    }

    private void update() {
        final Call<List<OrderServiceItem>> orderCall = orderService.getOrders(tableNumber, OrderStatusCache.getInstance().getIds().get("ordered"));
        final Call<List<OrderServiceItem>> readyCall = orderService.getOrders(tableNumber, OrderStatusCache.getInstance().getIds().get("ready"));

        final CoursesCache cache = CoursesCache.getInstance();

        cache.update(new CoursesCache.UpdateCallback() {
            Callback<List<OrderServiceItem>> callback = new Callback<List<OrderServiceItem>>() {
                @Override
                public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {

                    if (response.code() != 200) {
                        Toast.makeText(OrdersActivity.this, "Kunde inte hämta beställningar, kod " + response.code(), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        return;
                    }

                    HashMap<Integer, Integer> nonSpecCount = new HashMap<Integer, Integer>();

                    for (OrderServiceItem item : response.body()) {
                        Course course = cache.getCourses().get(item.getFoodId());

                        orderIds.add(new Integer(item.orderId));

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

                    for (int courseId : nonSpecCount.keySet()) {
                        orderItems.add(new Order.OrderItem(cache.getCourses().get(courseId),
                                nonSpecCount.get(courseId)));
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
            };

            @Override
            public void onSuccess() {

                orderIds.clear();
                orderItems.clear();
                specItemIds.clear();

                orderCall.enqueue(callback);
                readyCall.enqueue(callback);
            }
        });
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra("items", (Serializable) orderItems);
        intent.putExtra("table_id", tableNumber);
        startActivity(intent);
    }

    public void clearOrders(View view) {

        OrderService.BulkOrderUpdate bulkUpdate = new OrderService.BulkOrderUpdate();
        bulkUpdate.orderIds = new ArrayList<Integer>();
        bulkUpdate.orderIds.addAll(orderIds);

        bulkUpdate.orderPost = new OrderService.OrderPost();
        bulkUpdate.orderPost.orderStatusId = OrderStatusCache.getInstance().getIds().get("paid");

        Call<Void> call = orderService.bulkUpdateOrder(bulkUpdate);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() != 200) {
                    Toast.makeText(OrdersActivity.this,
                            "Gick inte att markera order som betalad, kod: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                orderConfirmPopup.remove();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(OrdersActivity.this,
                        "Gick inte att markera order som betalad",
                        Toast.LENGTH_SHORT);
            }
        });
    }

    public void showConfirmPopup(View view) {
        orderConfirmPopup = new OrderConfirmPopup(this);
    }

    public void dismissConfirmPopup(View view){
        orderConfirmPopup.remove();
    }

    public void removeSpecOrder(View view) {
        ListView listView = (ListView) findViewById(R.id.order_list);
        final int i = listView.getPositionForView((View) view.getParent());

        Order.OrderItem item = orderItems.get(i);

        OrderService.OrderUpdate orderUpdate = new OrderService.OrderUpdate();
        orderUpdate.orderStatusId = OrderStatusCache.getInstance().getIds().get("cancelled");

        Call<Void> call = orderService
                .updateOrder(
                        specItemIds.get(item.getCourse()),
                        orderUpdate);

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

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("table_number", tableNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Bord " + savedInstanceState.getInt("table_number", -3));
        super.onRestoreInstanceState(savedInstanceState);
    }
}
