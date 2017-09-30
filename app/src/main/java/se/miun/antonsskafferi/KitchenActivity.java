package se.miun.antonsskafferi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class KitchenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        KitchenOrdersAdapter adapter = new KitchenOrdersAdapter(this);
        ((ListView) findViewById(R.id.kitchen_list)).setAdapter(adapter);

        ArrayList<Order.OrderItem> list = new ArrayList<Order.OrderItem>(2);
        list.add(new Order.OrderItem("PonnyP-patch-plättar", 3));
        list.add(new Order.OrderItem("Grodlår", 2));

        Order order = new Order(3, list);
        adapter.add(order);

        list = new ArrayList<Order.OrderItem>(2);
        list.add(new Order.OrderItem("Känguru", "Utan vitlök"));
        list.add(new Order.OrderItem("Känguru", 2));

        order = new Order(4, list);
        adapter.add(order);
    }
}
