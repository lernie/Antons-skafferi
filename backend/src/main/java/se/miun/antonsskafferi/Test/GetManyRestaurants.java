package se.miun.antonsskafferi.Test;

import se.miun.antonsskafferi.Models.ARestaurant;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/manyrestaurants")
public class GetManyRestaurants {

    // The Java method will process HTTP GET requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() throws NullPointerException {
        List<ARestaurant> aRes = Restaurants.getAllRestaurants();

        ARestaurant ARU = new ARestaurant();
        ARU.setId(45);
        ARU.setRestaurantName("McDonalds");
        ARU.setCityName("Boston");
        System.out.println(ARU);


        return Response.ok(aRes).build();
    }
}