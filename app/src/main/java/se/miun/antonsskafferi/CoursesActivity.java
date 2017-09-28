package se.miun.antonsskafferi;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.ArrayList;


import se.miun.antonsskafferi.Courses.Courses;

import static android.R.attr.button;


public class CoursesActivity extends Activity{
    ListView listView;
    ArrayList<Courses> list = new ArrayList<Courses>();
    CourseAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        //  setContentView(R.layout.row);

        list.add(new Courses("Räkcocktail"));
        list.add(new Courses("Caprese"));
        list.add(new Courses("Kängurustek"));
        list.add(new Courses("Oxragu"));
        list.add(new Courses("Rabarberpaj"));
        list.add(new Courses("Wine"));

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



