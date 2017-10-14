package se.miun.antonsskafferi;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by joel on 2017-10-13.
 */

public class BackButtonActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
