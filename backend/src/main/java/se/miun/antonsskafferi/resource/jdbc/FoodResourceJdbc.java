package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.dao.InventoryItemDao;
import se.miun.antonsskafferi.dao.jdbc.FoodDaoJdbc;
import se.miun.antonsskafferi.dao.jdbc.InventoryItemDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/food")
public class FoodResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFood(
            @DefaultValue("-1")@QueryParam("type") int type,
            @DefaultValue("-1")@QueryParam("id") int id) {
        FoodDaoJdbc dao = new FoodDaoJdbc();
        return Response.ok(dao.getAll(type, id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFood(Food item) {
        FoodDaoJdbc dao = new FoodDaoJdbc();
        if (dao.insert(item)) {
            return Response.ok().build();
        } else {
            return Response.status(403).entity(new ErrorResponse(403,"unable to add item.")).build();
        }
    }

}
