package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TablesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        ListView listView = (ListView) findViewById(R.id.navigation_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        adapter.add("Test 1");
        adapter.add("Test 2");
    }

    public void goToOrder (View view){
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    public void goToKitchen (View view){
        Intent intent = new Intent(this, KitchenActivity.class);
        startActivity(intent);
    }
}
