package se.miun.antonsskafferi;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;


import se.miun.antonsskafferi.Courses.CourseListItem;
import se.miun.antonsskafferi.Courses.Course;


public class CoursesActivity extends Activity{
    ListView listView;
    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    CourseAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        //  setContentView(R.layout.row);

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


        }

    }

/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    final int position, long id) {
                Log.i("List View CLicked", "*******");
                Toast.makeText(CoursesActivity.this, "Added" + position,
                        Toast.LENGTH_LONG).show();
            }
        });
*/



