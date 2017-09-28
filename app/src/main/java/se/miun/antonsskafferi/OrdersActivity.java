package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class OrdersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        TableOrdersAdapter adapter = new TableOrdersAdapter(this);
        ((ListView) findViewById(R.id.orderList)).setAdapter(adapter);

        adapter.add("test");
        adapter.add("test");
        adapter.add("test");
        adapter.add("test");
        adapter.add("test");
        adapter.add("test");
    }

    public void goToCourses (View view){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

}
