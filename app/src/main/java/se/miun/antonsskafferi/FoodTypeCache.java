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

public class FoodTypeCache {
    private static FoodTypeCache instance;

    private Retrofit retrofit;

    private HashMap<Integer, String> types;
    private HashMap<String, Integer> ids;

    public static FoodTypeCache getInstance() {

        if(instance == null){
            instance = new FoodTypeCache();
        }

        return instance;
    }

    private FoodTypeCache() {

        types = new HashMap<Integer, String>();
        ids = new HashMap<String, Integer>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://simonarstam.com/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public HashMap<Integer, String> getTypes(){
        return types;
    }

    public HashMap<String, Integer> getIds(){
        return ids;
    }

    public void update(final UpdateCallback callback) {
        Call<List<FoodTypeService.FoodTypeServiceItem>> call =((FoodTypeService)
                retrofit.create(FoodTypeService.class)).getTypes();

        call.enqueue(new Callback<List<FoodTypeService.FoodTypeServiceItem>>() {
            @Override
            public void onResponse(Call<List<FoodTypeService.FoodTypeServiceItem>> call, Response<List<FoodTypeService.FoodTypeServiceItem>> response) {


                if (response == null || response.body() == null) {
                    clear();
                    return;
                }

                for (FoodTypeService.FoodTypeServiceItem item : response.body()) {
                    types.put(item.getId(), item.getName());
                    ids.put(item.getName(), item.getId());
                }

                if (callback != null) {
                    callback.onSuccess();
                }
            }

                @Override
                public void onFailure
                (Call < List < FoodTypeService.FoodTypeServiceItem >> call, Throwable t){
                    clear();

                    if (callback != null){
                        callback.onFail();
                    }
                }
        });
    }


    public void clear(){
        types.clear();
        ids.clear();
    }

    public static abstract class UpdateCallback {
        public void onSuccess(){ }
        public void onFail() { }
    }
}
