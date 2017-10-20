package se.miun.antonsskafferi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @POST("foodorder")
    Call<Void> postOrders(@Body List<OrderPost> orders);

    @PUT("foodorder/{orderId}")
    Call<Void> updateOrder(@Path("orderId") int orderId, @Body OrderUpdate order);

    @PUT("foodorder")
    Call<Void> bulkUpdateOrder(@Body BulkOrderUpdate bulk);

    @DELETE("foodorder")
    Call<Void> deleteOrders(@Query("table") int diningTableId, @Query("foodId") int foodId, @Query("count") int count);

    class OrderUpdate {
        Integer orderStatusId;
    }

    class BulkOrderUpdate {
        @SerializedName("foodOrderIds")
        List<Integer> orderIds;
        @SerializedName("foodOrder")
        OrderPost orderPost;
    }

    class OrderPost {
        String modification;
        Integer foodId, diningTableId, orderStatusId;
    }
}

