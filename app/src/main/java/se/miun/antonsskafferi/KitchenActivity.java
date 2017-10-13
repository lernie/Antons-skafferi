package se.miun.antonsskafferi;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KitchenActivity extends NavigationActivity {

    private ArrayList<Order> orderList;
    private KitchenOrdersAdapter adapter;
    HashMap<Integer, ArrayList<OrderServiceItem>> tablesOrdersList;

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

        OrderService service = retrofit.create(OrderService.class);

        final Call<List<OrderServiceItem>> call = service.getOrdersWithStatus(0);

        final CoursesCache cache = CoursesCache.getInstance();

        cache.update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                call.enqueue(new Callback<List<OrderServiceItem>>() {
                    @Override
                    public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {
                        if (response == null || response.body() == null) {
                            orderList.clear();
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
                                    orderItems.add(new Order.OrderItem(cache.getCourses().get(item.getFoodId()).getName(), item.getModification()));
                                }
                            }

                            for (int key : nonSpecCount.keySet()) {
                                orderItems.add(new Order.OrderItem(cache.getCourses().get(key).getName(), nonSpecCount.get(key)));
                            }

                            orderList.add(new Order(k, orderItems));
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                        orderList.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void removeOrder(View view) {
        GridView gridView = (GridView) findViewById(R.id.kitchen_list);
        orderList.remove(gridView.getPositionForView((View) view.getParent().getParent()));
        adapter.notifyDataSetChanged();
    }
}
