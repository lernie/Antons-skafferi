package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<List<OrderPost>> postOrders(@Body List<OrderPost> orders);

    class OrderPost {
        String modification;
        int foodId, diningTableId, orderStatusId;

        public OrderPost(int foodId, int diningTableId, int orderStatusId, String modification) {
            this.foodId = foodId;
            this.diningTableId = diningTableId;
            this.orderStatusId = orderStatusId;
            this.modification = modification;
        }
    }
}

