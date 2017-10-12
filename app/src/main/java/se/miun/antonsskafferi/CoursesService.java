package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by My on 10/10/2017.
 */

public interface CoursesService {
    @GET("food")
    Call<List<CoursesServiceItem>> getFoodType();

}
