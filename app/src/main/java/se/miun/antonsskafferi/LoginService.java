package se.miun.antonsskafferi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by My on 2017-10-14.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login")
    Call<User> post(@Field("username") String username,
                       @Field("password") String password);
}
