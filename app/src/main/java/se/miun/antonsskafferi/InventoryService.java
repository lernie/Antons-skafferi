package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by joel on 2017-10-10.
 */

public interface InventoryService {
    @GET("inventory")
    Call<List<InventoryServiceItem>> getInventory();
}
