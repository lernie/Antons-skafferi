package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by My on 10/13/2017.
 */


public interface OrderStatusService {
    @GET("orderstatus")
    Call<List<OrderStatusServiceItem>> getStatus();

 class OrderStatusServiceItem{

     int id;
     String name;


     public int getId() {
         return id;
     }

     public String getName() {
         return name;
     }
}
}
