package se.miun.antonsskafferi;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My on 2017-10-14.
 */

public class LoginCache {
    private static LoginCache instance;

    private Retrofit retrofit;

    private String authToken;

    private OkHttpClient client;

    public static LoginCache getInstance() {

        if (instance == null) {
            instance = new LoginCache();
        }
        return instance;
    }
/*
    private LoginCache() {

        authToken = new String();
        client = new OkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(" ")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public String getAuthToken() {
        return authToken;
    }

    public OkHttpClient getClient() {
        return client;
    }
    LoginService service = retrofit.create(LoginService.class);

    public void update (final UpdateCallback callback) {
        Call<LoginService> call = service.post("login", getAuthToken());

        call.enqueue(new Callback<LoginService>() {
            @Override
            public void onResponse(Call<LoginService> call, Response<LoginService> response) {

            }

            @Override
            public void onFailure(Call<LoginService> call, Throwable t) {

            }
        });
    }

    public void clear(){
        authToken.clear();
        client.clear();
    }

    public static abstract class UpdateCallback{
        public void onSuccess() { }
        public void onFail () { }
    }
    */
}
