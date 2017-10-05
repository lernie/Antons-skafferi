package se.miun.antonsskafferi;

import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;


import se.miun.antonsskafferi.Courses.CourseListItem;
import se.miun.antonsskafferi.Courses.Course;

public class CoursesActivity extends Activity {
    ListView listView;
    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    CourseAdapter userAdapter;
    Point p;
    Button spec;
    PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        list.add(new CourseListItem(new Course("Räkcocktail")));
        list.add(new CourseListItem(new Course("Caprese")));
        list.add(new CourseListItem(new Course("Kängurustek")));
        list.add(new CourseListItem(new Course("Oxragu")));
        list.add(new CourseListItem(new Course("Rabarberpaj")));
        list.add(new CourseListItem(new Course("Wine")));

        userAdapter = new CourseAdapter(CoursesActivity.this,
                R.layout.courses_list_item, list);
        listView = (ListView) findViewById(R.id.list_View);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);

    }

    public void Specpopup (View v){

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.course_layout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.courses_spec_popup, null);

        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

      /*  popupView.setOnTouchListener(new View.OnTouchListener(){
           @Override
            public boolean onTouch(View v, MotionEvent event){
               popupWindow.dismiss();
               return true;
           }
        });*/



    }


    public void closePopup(View v){
        popupWindow.dismiss();
    }
}

    /*

        public void toast(View v){
            Toast.makeText(CoursesActivity.this,
                    "Special Added", Toast.LENGTH_LONG).show();
        }
    }

*/
