package se.miun.antonsskafferi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class KitchenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        KitchenOrdersAdapter adapter = new KitchenOrdersAdapter(this);
        ((ListView) findViewById(R.id.kitchen_list)).setAdapter(adapter);

        adapter.add("");
        adapter.add("");
        adapter.add("");
        adapter.add("");
        adapter.add("");
    }
}
