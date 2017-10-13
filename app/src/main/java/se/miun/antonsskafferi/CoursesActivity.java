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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CoursesActivity extends BackButtonActivity {
    private ArrayList<CourseListItem> list;
    private PopupWindow popupWindow;
    private ArrayList<Order.OrderItem> orderedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        orderedItems = (ArrayList<Order.OrderItem>) getIntent().getSerializableExtra("items");

        list = new ArrayList<CourseListItem>();

        final CourseAdapter adapter = new CourseAdapter(CoursesActivity. this, list);

        CoursesCache.getInstance().update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                for (Course course : CoursesCache.getInstance().getCourses().values()) {
                    Order.OrderItem match = null;

                    for (Order.OrderItem item : orderedItems) {
                        if (item.getCourse().equals(course.getName())) {
                            match = item;
                            break;
                        }
                    }

                    if (match == null) {
                        list.add(new CourseListItem(course));
                    } else {
                        list.add(new CourseListItem(course, match.getCount()));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {
                adapter.notifyDataSetChanged();
            }
        });

        ListView listView = (ListView) findViewById(R.id.courses_list);
        listView.setItemsCanFocus(false);
        listView.setAdapter(adapter);
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
