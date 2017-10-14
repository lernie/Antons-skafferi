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

    private HashMap<Integer, Course> courses;
    private HashMap<Course, Integer> ids;

    public static CoursesCache getInstance() {
        if (instance == null) instance = new CoursesCache();
        return instance;
    }

    private CoursesCache() {
//        courses = (HashMap<Integer, Course>) Collections.synchronizedMap(new HashMap<Integer, Course>());

        courses = new HashMap<Integer, Course>();
        ids = new HashMap<Course, Integer>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://simonarstam.com:8080/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HashMap<Integer, Course> getCourses() {
        return courses;
    }

    public HashMap<Course, Integer> getIds() {
        return ids;
    }

    public void update(final UpdateCallback callback) {
        Call<List<CoursesServiceItem>> call = ((CoursesService) retrofit.create(CoursesService.class)).getCourses();

        call.enqueue(new Callback<List<CoursesServiceItem>>() {
            @Override
            public void onResponse(Call<List<CoursesServiceItem>> call, Response<List<CoursesServiceItem>> response) {

                if (response == null || response.body() == null) {
                    clear();
                    return;
                }

                for (CoursesServiceItem item : response.body()) {
                    Course course = new Course (item.getName(), item.getType(), item.getPrice(), item.getTimeToCook());
                    courses.put(item.getId(), course);
                    ids.put(course, item.getId());
                }

                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<List<CoursesServiceItem>> call, Throwable t) {
                clear();

                if (callback != null) {
                    callback.onFail();
                }
            }
        });
    }

    public void clear() {
        courses.clear();
        ids.clear();
    }

    public static abstract class UpdateCallback {
        public void onSuccess() {  }
        public void onFail() {  }
    }
}
