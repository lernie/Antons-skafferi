package miun.erfa1501;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/create")
public class ParamTest {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFromParam(List<ARestaurant> AR){
        System.out.println(AR.get(1).getRestaurantName());
        return Response.ok().build();
    }

    /*@QueryParam("cityName") String cn,
    @QueryParam("id") String id,
    @QueryParam("restaurantName") String rn*/

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hmm(){
        return "thinking";
    }
}
