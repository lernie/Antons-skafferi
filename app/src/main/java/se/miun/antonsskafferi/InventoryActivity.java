package se.miun.antonsskafferi;


import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.ListView;

public class InventoryActivity extends Activity {

    private ArrayList<Ingredient> inventoryList;
    private InventoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        inventoryList = new ArrayList<Ingredient>();
        adapter = new InventoryListAdapter(this, inventoryList);
        ((ListView) findViewById(R.id.inventory_list)).setAdapter(adapter);

        inventoryList.add(new Ingredient("Gino", 3, "st"));
        inventoryList.add(new Ingredient("Känguru", 2, "Kg"));
        inventoryList.add(new Ingredient("Cola", 1, "L"));

        adapter.notifyDataSetChanged();

    }
}
