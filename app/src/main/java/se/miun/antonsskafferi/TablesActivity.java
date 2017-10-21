package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TablesActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

    }
    public void goToKitchen (View view){
        Intent intent = new Intent(this, KitchenActivity.class);
        startActivity(intent);
    }
    public void goToOrder (View view){
        Intent intent = new Intent(this, OrdersActivity.class);

        switch (view.getId()){
            case R.id.table1_button:
                intent.putExtra("table_number", 1);
                break;
            case R.id.table2_button:
                intent.putExtra("table_number", 2);
                break;
            case R.id.table3_button:
                intent.putExtra("table_number", 3);
                break;
            case R.id.table4_button:
                intent.putExtra("table_number", 4);
                break;
            case R.id.table5_button:
                intent.putExtra("table_number", 5);
                break;
            case R.id.table6_button:
                intent.putExtra("table_number", 6);
                break;
            case R.id.table7_button:
                intent.putExtra("table_number", 7);
                break;
        }

        startActivity(intent);
    }
}
