package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TablesActivity extends Activity {

    DrawerLayout navigationMenuLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        ListView navigationMenuList = (ListView) findViewById(R.id.navigation_drawer);
        navigationMenuLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationMenuLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActionBar().setTitle("Navigation");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActionBar().setTitle(R.string.app_name);
            }
        };
        navigationMenuLayout.addDrawerListener(actionBarDrawerToggle);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        navigationMenuList.setAdapter(adapter);

        adapter.add("Test 1");
        adapter.add("Test 2");

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
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

    public void goToOrder (View view){
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    public void goToKitchen (View view){
        Intent intent = new Intent(this, KitchenActivity.class);
        startActivity(intent);
    }
}
