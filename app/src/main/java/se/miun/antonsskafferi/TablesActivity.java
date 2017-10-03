package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TablesActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
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
