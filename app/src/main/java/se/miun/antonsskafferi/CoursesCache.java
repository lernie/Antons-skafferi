package se.miun.antonsskafferi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joel on 2017-10-12.
 */

public class CoursesCache {
    private static CoursesCache instance;

    private Retrofit retrofit;

    Call<List<CoursesServiceItem>> call;

    private HashMap<Integer, Course> courses;

    public static CoursesCache getInstance() {
        if (instance == null) instance = new CoursesCache();
        return instance;
    }

    private CoursesCache() {
//        courses = (HashMap<Integer, Course>) Collections.synchronizedMap(new HashMap<Integer, Course>());

        courses = new HashMap<Integer, Course>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://37.139.13.250:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        call = ((CoursesService) retrofit.create(CoursesService.class)).getCourses();
    }

    public HashMap<Integer, Course> getCourses() {
        return courses;
    }

    public void update(final UpdateCallback callback) {
        call.enqueue(new Callback<List<CoursesServiceItem>>() {
            @Override
            public void onResponse(Call<List<CoursesServiceItem>> call, Response<List<CoursesServiceItem>> response) {

                if (response == null || response.body() == null) {
                    courses.clear();
                    return;
                }

                for (CoursesServiceItem item : response.body()) {
                    courses.put(item.getId(), new Course (item.getName(), item.getType(), item.getPrice(), item.getTimeToCook()));
                }

                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<List<CoursesServiceItem>> call, Throwable t) {
                courses.clear();

                if (callback != null) {
                    callback.onFail();
                }
            }
        });
    }

    public static abstract class UpdateCallback {
        public void onSuccess() {  }
        public void onFail() {  }
    }
}
