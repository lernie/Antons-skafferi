package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class OrdersActivity extends Activity {
    private TableOrdersAdapter adapter;
    private ArrayList<String> stringArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringArrayList = new ArrayList<String>();
        setContentView(R.layout.activity_orders);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new TableOrdersAdapter(this, stringArrayList);
        ((ListView) findViewById(R.id.orderList)).setAdapter(adapter);

        stringArrayList.add("test");
        stringArrayList.add("test");
        stringArrayList.add("test");
        stringArrayList.add("test");
        stringArrayList.add("test");
        stringArrayList.add("test");
        adapter.notifyDataSetChanged();
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void clearOrders(View view){
        adapter.clear();
    }

   /* public boolean clearSpecialOrder(View view){
        adapter.remove(R.id.orderList.indexOfChild(view));
    }*/

}
