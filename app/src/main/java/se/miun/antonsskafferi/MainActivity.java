package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, KitchenActivity.class);
        startActivity(intent);
    }

    public void goToTables (View view){
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }
}
