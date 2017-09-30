package se.miun.antonsskafferi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import se.miun.antonsskafferi.Courses.Order;

public class KitchenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        KitchenOrdersAdapter adapter = new KitchenOrdersAdapter(this);
        ((ListView) findViewById(R.id.kitchen_list)).setAdapter(adapter);

        ArrayList<String> list = new ArrayList<String>(2);
        list.add("PonnyP-patch-plättar");
        list.add("Grodlår");

        Order order = new Order(3, list);
        adapter.add(order);

        list = new ArrayList<String>(2);
        list.add("Känguru");
        list.add("Mys macka");

        order = new Order(4, list);
        adapter.add(order);
    }
}
