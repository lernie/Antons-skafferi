package se.miun.antonsskafferi;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
                .baseUrl("http://simonarstam.com/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void update(final UpdateCallback callback) {

        Call<ResponseBody> call = ((LoginService) retrofit.create(LoginService.class)).getToken(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("Response code", "" + response.code());
                if (response == null || response.body() == null) {
                    clear();
                    callback.onFail();

                    return;
                }

                try {
                    token = response.body().string();
                } catch (IOException e) {
                    token = "";
                    callback.onFail();
                    return;
                }

                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                clear();
                callback.onFail();
                Log.e("tmessage", t.getMessage());
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

    public void intercept() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader(" ", " ").build();
                return chain.proceed(newRequest);
            }
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        // Set the custom client when building adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" ")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}


