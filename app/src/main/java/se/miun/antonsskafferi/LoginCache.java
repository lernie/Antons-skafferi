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

    private ServiceGenerator serviceGenerator;
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

     //   serviceGenerator = new ServiceGenerator();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://simonarstam.com/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //TODO: remember to remove
        serviceGenerator = new ServiceGenerator(token);
        update(null);
    }


    public void update(final UpdateCallback callback) {

        Call<ResponseBody> call = ((LoginService) retrofit.create(LoginService.class)).getToken(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("Response code", "" + response.code());
                if (response == null || response.body() == null) {
                    if(callback != null){
                        callback.onFail();
                    }
                    return;
                }

                try {
                    token = response.body().string();
                    serviceGenerator = new ServiceGenerator(token);
                } catch (IOException e) {
                    token = "";
                    if(callback != null) {
                        callback.onFail();
                    }
                    return;
                }
                if(callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(callback != null) {
                    callback.onFail();
                }
                Log.e("tmessage", t.getMessage());
            }
        });
    }

    public static abstract class UpdateCallback {
        public void onSuccess() {
        }

        public void onFail() {
        }
    }

    public ServiceGenerator getGen(){ return serviceGenerator;}
}




