package se.miun.antonsskafferi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class CoursesActivity extends Activity {
    ListView listView;
    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    CourseAdapter userAdapter;
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
        listView = (ListView) findViewById(R.id.courses_list);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);

    }

    public void showSpecPopup(View v) {

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.course_layout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.courses_spec_popup, null);

        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
    }

    public void closePopup(View v) {
        popupWindow.dismiss();
    }
}
