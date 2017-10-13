package se.miun.antonsskafferi;

import android.os.Bundle;
import android.app.Activity;

public class TablesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
