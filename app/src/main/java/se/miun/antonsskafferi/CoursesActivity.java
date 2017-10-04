package se.miun.antonsskafferi;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CoursesActivity extends Activity {
    ListView listView;
    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    CourseAdapter userAdapter;
   Button spec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);

        list.add(new CourseListItem(new Course("Räkcocktail")));
        list.add(new CourseListItem(new Course("Caprese")));
        list.add(new CourseListItem(new Course("Kängurustek")));
        list.add(new CourseListItem(new Course("Oxragu")));
        list.add(new CourseListItem(new Course("Rabarberpaj")));
        list.add(new CourseListItem(new Course("Wine")));

        userAdapter = new CourseAdapter(CoursesActivity.this,
                R.layout.row, list);
        listView = (ListView) findViewById(R.id.list_View);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);
        spec = (Button)findViewById(R.id.Special);
            }

        public void toast(View v){
            Toast.makeText(CoursesActivity.this,
                    "Special Added", Toast.LENGTH_LONG).show();
        }
    }


