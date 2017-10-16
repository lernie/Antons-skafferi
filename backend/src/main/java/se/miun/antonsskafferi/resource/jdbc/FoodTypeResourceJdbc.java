package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.dao.jdbc.FoodTypeDaoJdbc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/foodtype")
public class FoodTypeResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoodTypes(){
        FoodTypeDaoJdbc dao = new FoodTypeDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }
}
