package se.miun.antonsskafferi;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by My on 10/13/2017.
 */

public class OrderStatusCache {
    private static OrderStatusCache instance;

    private Retrofit retrofit;

    private HashMap<Integer, String> status;
    private HashMap<String, Integer> ids;

    public static OrderStatusCache getInstance() {

        if(instance == null) {
            instance = new OrderStatusCache();

        }

        return instance;
    }

    private OrderStatusCache() {

        status = new HashMap<Integer, String>();
        ids = new HashMap<String, Integer>();

        retrofit = new Retrofit.Builder()
                .baseUrl(" ")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HashMap<Integer, String> getStatus(){ return status; }

    public HashMap<String, Integer> getIds () { return ids; }

    public void update (final UpdateCallback callback) {
        Call<List<OrderStatusService.OrderStatusServiceItem>> call = ((OrderStatusService)
        retrofit.create(OrderStatusService.class)).getStatus();

        call.enqueue(new Callback<List<OrderStatusService.OrderStatusServiceItem>>() {
            @Override
           public void onResponse(Call<List<OrderStatusService.OrderStatusServiceItem>> call,
                                  Response<List<OrderStatusService.OrderStatusServiceItem>> response){

                if (response == null || response.body() == null) {
                    clear();
                    return;
                }

                for (OrderStatusService.OrderStatusServiceItem item : response.body()) {
                    status.put(item.getId(), item.getName());
                    ids.put(item.getName(), item.getId());
                }

                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure
                    (Call<List<OrderStatusService.OrderStatusServiceItem>> call, Throwable t){
                clear();

                if (callback != null) {
                    callback.onFail();
                }
            }
        });
    }

    public void clear() {
        status.clear();
        ids.clear();
    }

    public static abstract class UpdateCallback {
        public void onSuccess() { }
        public void onFail() { }
    }
}
