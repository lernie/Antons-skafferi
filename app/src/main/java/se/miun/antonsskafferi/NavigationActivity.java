package se.miun.antonsskafferi;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by joel on 2017-10-03.
 */


public class NavigationActivity extends Activity {

    private DrawerLayout navigationMenuLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence activityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.navigation_layout);

        activityTitle = getTitle();

        navigationMenuLayout = findViewById(R.id.navigation_drawer_layout);

        // TODO: Change R.string.app_name to something proper
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationMenuLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("Navigation");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(activityTitle);
            }
        };

        navigationMenuLayout.addDrawerListener(actionBarDrawerToggle);

        ListView navigationMenuList = (ListView) findViewById(R.id.navigation_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        navigationMenuList.setAdapter(adapter);

        adapter.add("Test 1");
        adapter.add("Test 2");

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
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
