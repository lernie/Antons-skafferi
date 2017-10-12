package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joel on 2017-10-09.
 */

public interface OrderService {
    @GET("foodorder")
    Call<List<OrderServiceItem>> getOrders(@Query("table") int table, @Query("status") int status);

    @GET("foodorder")
    Call<List<OrderServiceItem>> getOrdersOnTable(@Query("table") int table);

    @GET("foodorder")
    Call<List<OrderServiceItem>> getOrdersWithStatus(@Query("status") int status);

    @GET("foodorder")
    Call<List<OrderServiceItem>> getAllOrders();
}

