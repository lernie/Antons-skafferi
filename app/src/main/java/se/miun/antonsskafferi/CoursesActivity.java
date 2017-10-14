package se.miun.antonsskafferi;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoursesActivity extends BackButtonActivity {
    private ArrayList<CourseListItem> list;
    private PopupWindow popupWindow;
    private ArrayList<Order.OrderItem> orderedItems;
    private int tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        orderedItems = (ArrayList<Order.OrderItem>) getIntent().getSerializableExtra("items");
        tableId = getIntent().getIntExtra("table_id", -1);

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
                list.clear();
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

    public void saveOrder(View view) {
        ArrayList<OrderService.OrderPost> newOrders = new ArrayList<OrderService.OrderPost>();

        for (CourseListItem item : list) {
            Order.OrderItem match = null;

            for (Order.OrderItem order : orderedItems) {
                if (item.getCourse().getName().equals(order.getCourse())) {
                    match = order;
                    break;
                }
            }

            // TODO: Check for equivalence, so that orders may be removed
            if ((match == null && item.getCount() > 0) || (match != null && match.getCount() < item.getCount())) {
                int id = CoursesCache.getInstance().getIds().get(item.getCourse());

                for (int i = 0; i < item.getCount(); i++) {
                    newOrders.add(new OrderService.OrderPost(id, tableId, 0, ""));
                }
            }
        }

        if (!newOrders.isEmpty()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.ip_address))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Call<Void> call = retrofit.create(OrderService.class)
                    .postOrders(newOrders);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast toast = Toast.makeText(CoursesActivity.this, "Kunde inte spara", Toast.LENGTH_SHORT);
                    Log.w("Failed POST", "Failed to post new orders", t);
                    toast.show();
                }
            });
        } else {
            Toast toast = Toast.makeText(CoursesActivity.this, "Inget att spara", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
