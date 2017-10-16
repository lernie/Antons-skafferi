package se.miun.antonsskafferi;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joel on 2017-10-13.
 */

public class UnitCache {
    private static UnitCache instance;

    private HashMap<Integer, String> units;
    private HashMap<String, Integer> ids;
    private Retrofit retrofit;

    public static UnitCache getInstance() {
        if (instance == null) {
            instance = new UnitCache();
        }

        return instance;
    }

    private UnitCache() {
        units = new HashMap<Integer, String>();
        ids = new HashMap<String, Integer>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://simonarstam.com/antons-skafferi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HashMap<Integer, String> getUnits() {
        return units;
    }

    public void update(final UpdateCallback callback) {
        Call<List<UnitService.Unit>> call = ((UnitService) retrofit.create(UnitService.class)).getUnits();

        call.enqueue(new Callback<List<UnitService.Unit>>() {
            @Override
            public void onResponse(Call<List<UnitService.Unit>> call, Response<List<UnitService.Unit>> response) {

                if (response == null || response.body() == null) {
                    clear();
                    return;
                }

                for (UnitService.Unit item : response.body()) {
                    String unit = item.getPrefix();
                    units.put(item.getId(), unit);
                    ids.put(unit, item.getId());
                }

                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<List<UnitService.Unit>> call, Throwable t) {
                clear();

                if (callback != null) {
                    callback.onFail();
                }
            }
        });
    }

    public void clear() {
        units.clear();
        ids.clear();
    }

    public static abstract class UpdateCallback {
        public void onSuccess() {  }
        public void onFail() {  }
    }
}
