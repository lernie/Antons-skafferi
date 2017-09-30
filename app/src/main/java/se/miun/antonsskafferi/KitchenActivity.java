package se.miun.antonsskafferi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class KitchenActivity extends Activity {

    private ArrayList<Order> orderList;
    private KitchenOrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        orderList = new ArrayList<>();
        adapter = new KitchenOrdersAdapter(this, orderList);
        
        ((ListView) findViewById(R.id.kitchen_list)).setAdapter(adapter);

        ArrayList<Order.OrderItem> list = new ArrayList<Order.OrderItem>(2);
        list.add(new Order.OrderItem("PonnyP-patch-plättar", 3));
        list.add(new Order.OrderItem("Grodlår", 2));

        Order order = new Order(3, list);
        orderList.add(order);

        list = new ArrayList<Order.OrderItem>(2);
        list.add(new Order.OrderItem("Känguru", "Utan vitlök"));
        list.add(new Order.OrderItem("Känguru", 2));

        order = new Order(4, list);
        orderList.add(order);
    }

    public void removeOrder(View view) {
        ListView listView = findViewById(R.id.kitchen_list);
        orderList.remove(listView.getPositionForView((View) view.getParent().getParent()));
        adapter.notifyDataSetChanged();
    }
}
