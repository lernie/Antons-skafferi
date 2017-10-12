package se.miun.antonsskafferi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KitchenActivity extends NavigationActivity {

    private ArrayList<Order> orderList;
    private KitchenOrdersAdapter adapter;
    ArrayList<ArrayList<OrderServiceItem>> tablesOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        orderList = new ArrayList<>();
        adapter = new KitchenOrdersAdapter(this, orderList);
        
        ((GridView) findViewById(R.id.kitchen_list)).setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService service = retrofit.create(OrderService.class);

        Call<List<OrderServiceItem>> call = service.getOrdersWithStatus(1);

        tablesOrdersList = new ArrayList<ArrayList<OrderServiceItem>>(7);

        for (int i = 0; i < 7; i++) {
            tablesOrdersList.add(new ArrayList<OrderServiceItem>());
        }

        call.enqueue(new Callback<List<OrderServiceItem>>() {
            @Override
            public void onResponse(Call<List<OrderServiceItem>> call, Response<List<OrderServiceItem>> response) {
                if (response == null || response.body() == null) {
                    orderList.clear();
                    adapter.notifyDataSetChanged();
                    return;
                }

                for (OrderServiceItem item : response.body()) {
                    tablesOrdersList.get(item.getDiningTableOrderId())
                            .add(item);
                }

                for (int i = 0; i < tablesOrdersList.size(); i++) {
                    if (tablesOrdersList.get(i).isEmpty()) continue;

                    ArrayList<Order.OrderItem> orderItems = new ArrayList<Order.OrderItem>();

                    HashMap<Integer, Integer> nonModifiedCount = new HashMap<Integer, Integer>();

                    for (OrderServiceItem item : tablesOrdersList.get(i))  {
                        if ("".equals(item.getModification())) {
                            if (nonModifiedCount.containsKey(item.getFoodId())) {
                                nonModifiedCount.put(item.getFoodId(), nonModifiedCount.get(item.getFoodId()) + 1);
                            } else {
                                nonModifiedCount.put(item.getFoodId(), 1);
                            }
                        } else {
                            orderItems.add(new Order.OrderItem("" + item.getFoodId(), item.getModification()));
                        }
                    }

                    for (Integer key : nonModifiedCount.keySet()) {
                        orderItems.add(new Order.OrderItem("" + key, nonModifiedCount.get(key)));
                    }

                    orderList.add(new Order(i + 1, orderItems));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderServiceItem>> call, Throwable t) {
                orderList.clear();
                adapter.notifyDataSetChanged();
            }
        });

//        ArrayList<Order.OrderItem> list = new ArrayList<Order.OrderItem>(2);
//        list.add(new Order.OrderItem("PonnyP-patch-plättar", 3));
//        list.add(new Order.OrderItem("Grodlår", 2));
//
//        Order order = new Order(3, list);
//        orderList.add(order);
//
//        list = new ArrayList<Order.OrderItem>(4);
//        list.add(new Order.OrderItem("My", 2));
//        list.add(new Order.OrderItem("Kalle", 2));
//        list.add(new Order.OrderItem("Wictor", 2));
//        list.add(new Order.OrderItem("PonnyP", 2));
//
//        orderList.add(new Order(7, list));
//
//        list = new ArrayList<Order.OrderItem>(2);
//        list.add(new Order.OrderItem("Känguru", "Utan vitlök"));
//        list.add(new Order.OrderItem("Känguru", 2));
//
//        order = new Order(4, list);
//        orderList.add(order);

//        adapter.notifyDataSetChanged();
    }

    public void removeOrder(View view) {
        GridView gridView = (GridView) findViewById(R.id.kitchen_list);
        orderList.remove(gridView.getPositionForView((View) view.getParent().getParent()));
        adapter.notifyDataSetChanged();
    }
}
