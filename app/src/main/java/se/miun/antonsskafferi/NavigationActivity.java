package se.miun.antonsskafferi;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by joel on 2017-10-03.
 */


public class NavigationActivity extends AppCompatActivity {

    private DrawerLayout navigationMenuLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence activityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.navigation_layout);

        activityTitle = getTitle();

        navigationMenuLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        // TODO: Change R.string.app_name to something proper
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationMenuLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(activityTitle);
            }
        };

        navigationMenuLayout.addDrawerListener(actionBarDrawerToggle);

        final NavigationView navView = (NavigationView) findViewById(R.id.navigation_drawer);

        int itemId = getIntent().getIntExtra("item_id", -1);

        if (itemId >= 0) {
            navView.setCheckedItem(itemId);
        } else {
            navView.setCheckedItem(R.id.nav_table_button);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                navigationMenuLayout.closeDrawers();

                Intent intent = null;

                switch (item.getItemId()) {
                    case R.id.nav_table_button:
                        intent = new Intent(NavigationActivity.this, TablesActivity.class);
                        break;
                    case R.id.nav_kitchen_button:
                        intent = new Intent(NavigationActivity.this, KitchenActivity.class);
                        break;
                    case R.id.nav_inventory_button:
                        intent = new Intent(NavigationActivity.this, InventoryActivity.class);
                        break;
                    case R.id.nav_schedule_button:
                        intent = new Intent(NavigationActivity.this, EmployeeScheduleActivity.class);
                        break;
                }

                if (intent != null) {
                    intent.putExtra("item_id", item.getItemId());
                    startActivity(intent);
                    finish();
                }

                return false;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(layoutResID, (ViewGroup) navigationMenuLayout, false);

        navigationMenuLayout.addView(view, 0); // Index 0, in order to insert the layout before the ListView
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) return true;

        return super.onOptionsItemSelected(item);
    }
}
