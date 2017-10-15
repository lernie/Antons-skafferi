package se.miun.antonsskafferi;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My on 2017-10-14.
 */

public class LoginCache {

    private String token;
    private User user;
    private static LoginCache instance;

    private Retrofit retrofit;
    
    public static LoginCache getInstance() {
        if (instance == null) instance = new LoginCache();
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    private LoginCache() {
        token = "";

        retrofit = new Retrofit.Builder()
                .baseUrl("http://simonarstam.com:8080/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void update(final UpdateCallback callback) {

        Call<String> call = ((LoginService) retrofit.create(LoginService.class)).getToken(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response == null || response.body() == null) {
                    clear();
                    return;
                }

                token = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                clear();
            }
        });
    }

    public void clear() {
       token = "";
    }

    public static abstract class UpdateCallback {
        public void onSuccess() {  }
        public void onFail() {  }
    }
}


