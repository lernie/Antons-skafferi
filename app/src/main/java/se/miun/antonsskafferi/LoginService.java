package se.miun.antonsskafferi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by My on 2017-10-14.
 */

public interface LoginService {
    @POST("login")
    Call<ResponseBody> getToken(@Body User user);

    @POST("employee")
    Call<ResponseBody> getlogin(@Body User user);
}
