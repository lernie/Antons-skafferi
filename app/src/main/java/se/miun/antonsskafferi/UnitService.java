package se.miun.antonsskafferi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by joel on 2017-10-13.
 */

public interface UnitService {
    @GET("measurement")
    Call<List<Unit>> getUnits();

    class Unit {
        int id;
        String name, prefix;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
