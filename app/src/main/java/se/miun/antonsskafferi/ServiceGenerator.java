package se.miun.antonsskafferi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My on 10/19/2017.
 */

public class ServiceGenerator {

    private String token;
    private Retrofit retrofit;

    private OkHttpClient.Builder Client = new OkHttpClient.Builder();

    public  <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public ServiceGenerator(String token){
        this.token = token;

        retrofit =
            new Retrofit.Builder()
                .baseUrl("http://simonarstam.com/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void clearAuth() {
        token = "";
    }

}