package se.miun.antonsskafferi;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KitchenActivity extends NavigationActivity {

    private ArrayList<Order> orderList;
    private KitchenOrdersAdapter adapter;
    private OrderService service;
    HashMap<Integer, ArrayList<OrderServiceItem>> tablesOrdersList;
    ScheduledExecutorService scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        orderList = new ArrayList<Order>();
        adapter = new KitchenOrdersAdapter(this, orderList);

        ((GridView) findViewById(R.id.kitchen_list)).setAdapter(adapter);

        tablesOrdersList = new HashMap<Integer, ArrayList<OrderServiceItem>>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OrderService.class);

        final CoursesCache cache = CoursesCache.getInstance();

        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                cache.update(new CoursesCache.UpdateCallback() {
                    @Override
                    public void onSuccess() {
                        final Call<List<OrderServiceItem>> call = service.getOrdersWithStatus(0);

                        call.enqueue(new Callback<List<OrderServiceItem>>() {
                            @Override
                            public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {
                                orderList.clear();
                                tablesOrdersList.clear();

                                if (response == null || response.body() == null) {
                                    adapter.notifyDataSetChanged();
                                    return;
                                }

                                for (OrderServiceItem item : response.body()) {
                                    if (!tablesOrdersList.containsKey(item.getDiningTableId())) {
                                        tablesOrdersList.put(item.getDiningTableId(), new ArrayList<OrderServiceItem>());
                                    }

                                    tablesOrdersList.get(item.getDiningTableId())
                                            .add(item);
                                }

                                for (int k : tablesOrdersList.keySet()) {

                                    ArrayList<Order.OrderItem> orderItems = new ArrayList<Order.OrderItem>();

                                    HashMap<Integer, Integer> nonSpecCount = new HashMap<Integer, Integer>();

                                    for (OrderServiceItem item : tablesOrdersList.get(k)) {
                                        if (!item.isSpecial()) {
                                            if (nonSpecCount.containsKey(item.getFoodId())) {
                                                nonSpecCount.put(item.getFoodId(), nonSpecCount.get(item.getFoodId()) + 1);
                                            } else {
                                                nonSpecCount.put(item.getFoodId(), 1);
                                            }
                                        } else {
                                            orderItems.add(new Order.OrderItem(cache.getCourses().get(item.getFoodId()), item.getModification()));
                                        }
                                    }

                                    for (int key : nonSpecCount.keySet()) {
                                        orderItems.add(new Order.OrderItem(cache.getCourses().get(key), nonSpecCount.get(key)));
                                    }

                                    orderList.add(new Order(k, orderItems));
                                }

                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                                orderList.clear();
                                tablesOrdersList.clear();
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void finish() {
        super.finish();
        scheduler.shutdown();
    }

    public void removeOrder(View view) {
        GridView gridView = (GridView) findViewById(R.id.kitchen_list);

        final Order order = orderList.get(gridView.getPositionForView((View) view.getParent().getParent()));

        OrderService.BulkOrderUpdate orderUpdate = new OrderService.BulkOrderUpdate();
        orderUpdate.orderIds = new ArrayList<Integer>();
        orderUpdate.orderPost = new OrderService.OrderPost();
        orderUpdate.orderPost.orderStatusId = 1;

        for (OrderServiceItem item : tablesOrdersList.get(order.getTable())) {
            orderUpdate.orderIds.add(item.orderId);
        }

        Call<Void> call = service.bulkUpdateOrder(orderUpdate);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() != 200) {
                    Toast.makeText(KitchenActivity.this, "Kunde inte checka av ordern, kod: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                orderList.remove(order);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast();
            }

            private void showToast() {
                Toast.makeText(KitchenActivity.this, "Kunde inte checka av ordern", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
