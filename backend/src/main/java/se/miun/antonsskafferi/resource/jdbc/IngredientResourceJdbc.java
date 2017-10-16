package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.dao.jdbc.IngredientDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ingredient")
public class IngredientResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredients(/*@DefaultValue("-1")@QueryParam("measurementId") int measurementId*/) {
        IngredientDaoJdbc dao = new IngredientDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(Ingredient ingredient) {
        IngredientDaoJdbc dao = new IngredientDaoJdbc();
        dao.insert(ingredient);
        return Response.ok().build();
    }
}
