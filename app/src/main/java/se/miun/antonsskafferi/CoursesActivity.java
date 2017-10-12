package se.miun.antonsskafferi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CoursesActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<CourseListItem> list = new ArrayList<CourseListItem>();
    CourseAdapter userAdapter;
    PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

       /* list.add(new CourseListItem(new Course("Räkcocktail")));
        list.add(new CourseListItem(new Course("Caprese")));
        list.add(new CourseListItem(new Course("Kängurustek")));
        list.add(new CourseListItem(new Course("Oxragu")));
        list.add(new CourseListItem(new Course("Rabarberpaj")));
        list.add(new CourseListItem(new Course("Wine")));
*/
        userAdapter = new CourseAdapter(CoursesActivity.this,
                R.layout.courses_list_item, list);
        listView = (ListView) findViewById(R.id.courses_list);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://37.139.13.250:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoursesService coursesService = retrofit.create(CoursesService.class);

        Call<List<CoursesServiceItem>> call = coursesService.getFoodType();

        call.enqueue(new Callback<List<CoursesServiceItem>>() {
            @Override
            public void onResponse(Call<List<CoursesServiceItem>> call, Response<List<CoursesServiceItem>> response) {

                if (response == null || response.body() == null) {
                    list.clear();
                    userAdapter.notifyDataSetChanged();
                    return;
                }

                for (CoursesServiceItem food : response.body()) {
                    list.add(new CourseListItem (new Course (food.getName())));
                }

                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CoursesServiceItem>> call, Throwable t) {
                list.add(new CourseListItem(new Course("Svält")));
                userAdapter.notifyDataSetChanged();
            }
        });


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
    public void goToOrder (View view){
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }
}
