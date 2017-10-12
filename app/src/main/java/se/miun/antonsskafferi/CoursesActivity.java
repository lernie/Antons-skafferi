package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class CoursesActivity extends BackButtonActivity {
    private ListView listView;
    private ArrayList<CourseListItem> list;
    private CourseAdapter userAdapter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        list = new ArrayList<CourseListItem>();

        userAdapter = new CourseAdapter(CoursesActivity.this,
                R.layout.courses_list_item, list);

        CoursesCache.getInstance().update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                for (Course course : CoursesCache.getInstance().getCourses().values()) {
                    list.add(new CourseListItem(course));
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {
                userAdapter.notifyDataSetChanged();
            }
        });

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

    public void goToOrder (View view) {
        // TODO: Put saving stuff to database here
    }
}
