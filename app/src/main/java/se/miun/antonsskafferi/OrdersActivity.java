package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class OrdersActivity extends Activity {

    private TableOrdersAdapter adapter;
    private ArrayList<Order.OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        orderItems = new ArrayList<Order.OrderItem>();
        adapter = new TableOrdersAdapter(this, orderItems);
        ((ListView) findViewById(R.id.orderList)).setAdapter(adapter);

        orderItems.add(new Order.OrderItem("Gino", 3));
        orderItems.add(new Order.OrderItem("Känguru", "Utan lök"));
        orderItems.add(new Order.OrderItem("Cola", 1));

        adapter.notifyDataSetChanged();
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void clearOrders(View view){
        adapter.clear();
    }

    public void clearSpec(View view){
        ListView listView = findViewById(R.id.orderList);
        int i = listView.getPositionForView((View) view.getParent());
        orderItems.remove(i);
        adapter.notifyDataSetChanged();
    }

}
