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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoursesActivity extends BackButtonActivity {
    private ArrayList<CourseListItem> courseList;
    private PopupWindow popupWindow;
    private ArrayList<Order.OrderItem> orderedItems;
    private int tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        orderedItems = (ArrayList<Order.OrderItem>) getIntent().getSerializableExtra("items");
        tableId = getIntent().getIntExtra("table_id", -1);

        courseList = new ArrayList<CourseListItem>();

        final CourseAdapter adapter = new CourseAdapter(CoursesActivity. this, courseList);

        CoursesCache.getInstance().update(new CoursesCache.UpdateCallback() {
            @Override
            public void onSuccess() {
                for (Course course : CoursesCache.getInstance().getCourses().values()) {
                    Order.OrderItem match = null;

                    for (Order.OrderItem item : orderedItems) {
                        if (item.getCourse().getName().equals(course.getName())) {
                            match = item;
                            break;
                        }
                    }

                    if (match == null) {
                        courseList.add(new CourseListItem(course));
                    } else {
                        courseList.add(new CourseListItem(course, match.getCount()));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {
                courseList.clear();
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
        HashMap<Integer, Integer> deleteOrders = new HashMap<Integer, Integer>();

        for (CourseListItem item : courseList) {
            Order.OrderItem match = null;

            for (Order.OrderItem order : orderedItems) {
                if (item.getCourse().getName().equals(order.getCourse().getName())) {
                    match = order;
                    break;
                }
            }

            int courseId = CoursesCache.getInstance().getIds().get(item.getCourse());

            if (match != null) {
                if (match.getCount() == item.getCount()) {
                    continue;
                }

                if (match.getCount() < item.getCount()) {
                    for (int i = 0; i < item.getCount() - match.getCount(); i++) {
                        OrderService.OrderPost orderPost = new OrderService.OrderPost();
                        orderPost.foodId = courseId;
                        orderPost.diningTableId = tableId;
                        orderPost.modification = "";
                        newOrders.add(orderPost);
                    }
                } else {
                    deleteOrders.put(courseId, match.getCount() - item.getCount());
                }
            } else if (item.getCount() > 0) {
                for (int i = 0; i < item.getCount(); i++) {
                    OrderService.OrderPost orderPost = new OrderService.OrderPost();
                    orderPost.foodId = courseId;
                    orderPost.diningTableId = tableId;
                    orderPost.orderStatusId = 0;
                    orderPost.modification = "";
                    newOrders.add(orderPost);
                }
            }
        }

        class Counter {
            private int count = 0;
            private int goal;

            public Counter(int goal) {
                this.goal = goal;
            }

            public void inc() {
                count++;

                if (count == goal) {
                    onBackPressed();
                }
            }
        }

        final Counter counter = new Counter(deleteOrders.size() + (newOrders.isEmpty() ? 0 : 1));

        if (!deleteOrders.isEmpty() || !newOrders.isEmpty()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.ip_address))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OrderService service = retrofit.create(OrderService.class);

            if (!deleteOrders.isEmpty()) {
                for (final Map.Entry<Integer, Integer> entry : deleteOrders.entrySet()) {
                    Call<Void> call = service.deleteOrders(tableId, entry.getKey(), entry.getValue());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() != 200) {
                                showToast();
                            } else {
                                counter.inc();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            showToast();
                        }

                        void showToast() {
                            Toast.makeText(CoursesActivity.this, "Kunde inte ta bort rätt med id " + entry.getKey(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            if (!newOrders.isEmpty()) {
                Call<Void> call = retrofit.create(OrderService.class)
                        .postOrders(newOrders);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            counter.inc();
                        } else {
                            Toast.makeText(CoursesActivity.this, "Kunde inte spara, kod: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CoursesActivity.this, "Kunde inte spara", Toast.LENGTH_SHORT).show();
                        Log.w("Failed POST", "Failed to post new orders", t);
                    }
                });
            }
        } else {
            Toast.makeText(CoursesActivity.this, "Inget att spara", Toast.LENGTH_SHORT).show();
        }
    }
}
