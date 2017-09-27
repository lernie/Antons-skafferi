package miun.erfa1501;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/manyrestaurants")
public class GetManyRestaurants {

    // The Java method will process HTTP GET requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ARestaurant> read() throws NullPointerException {
        Restaurants r = new Restaurants();
        List<ARestaurant> ARes = r.getAllRestaurants();

        ARestaurant ARU = new ARestaurant();
        ARU.setId(45);
        ARU.setRestaurantName("McDonalds");
        ARU.setCityName("Boston");
        System.out.println(ARU);
        return ARes;
    }
}