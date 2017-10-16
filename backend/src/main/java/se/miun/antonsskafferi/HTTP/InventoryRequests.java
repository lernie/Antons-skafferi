package se.miun.antonsskafferi.HTTP;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.istack.internal.logging.Logger;
import se.miun.antonsskafferi.Database.IngredientDB;
import se.miun.antonsskafferi.Database.InventoryDB;
import se.miun.antonsskafferi.Database.MeasurementDB;
import se.miun.antonsskafferi.Models.*;
import se.miun.antonsskafferi.dao.FoodDao;
import se.miun.antonsskafferi.dao.jdbc.FoodDaoJdbc;


@Path("/api")
public class InventoryRequests {

    @GET
    @Path("/measurement")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMeasurements() {
        return Response.ok(MeasurementDB.getAllMeasurements()).build();
    }

    @POST
    @Path("/measurement")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Measurement measurement) {
        MeasurementDB.insertMeasurement(measurement);
        return Response.ok().build();
    }


    @DELETE
    @Path("/measurement/{id}")
    public Response delMeasurement(@PathParam("id") int id) {
        if (MeasurementDB.delMeasurement(id)) {
            return Response.ok().build();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("/ingredient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredients(@DefaultValue("-1")@QueryParam("measurementId") int measurementId) {
        return Response.ok(IngredientDB.getAllIngredients(measurementId)).build();
    }

    @POST
    @Path("/ingredient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(Ingredient ingredient) {
        IngredientDB.insertIngredient(ingredient);
        return Response.ok().build();
    }

    @POST
    @Path("/inventory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(InventoryItem item) {
        if (InventoryDB.insertInventoryItem(item)) {
            return Response.ok().build();
        } else {
            return Response.status(403).entity(new ErrorResponse(403,"unable to add item.")).build();
        }
    }

    @GET
    @Path("/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory() {
        return Response.ok(InventoryDB.getInventory()).build();
    }

    @POST
    @Path("/inventory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryItem(InventoryItem item, @PathParam("id") int id) {
        item.setIngredientId(id);
        InventoryDB.updateInventoryItem(item);
        return Response.ok().build();
    }

    @GET
    @Path("/food")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFood(
        @DefaultValue("-1")@QueryParam("type") int type,
        @DefaultValue("-1")@QueryParam("id") int id) {
        FoodDaoJdbc foodDao = new FoodDaoJdbc();
        return Response.ok(foodDao.getAll(type, id)).build();
    }

    @POST
    @Path("/food")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFood(Food item) {
        if(!item.isValid()) {
            return Response
                    .status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "invalid input"))
                    .build();
        }
        FoodDaoJdbc foodDao = new FoodDaoJdbc();
        if (!foodDao.insert(item)) {
            return Response
                    .status(403)
                    .entity(new ErrorResponse(403,"unable to add item."))
                    .build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/food/{id}")
    public Response deleteFood(@PathParam("id") int id) {
        FoodDaoJdbc foodDao = new FoodDaoJdbc();
        if(!foodDao.delete(id)) {
            return Response
                    .status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "unable to delete"))
                    .build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/foodtype")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoodTypes(){
        return Response.ok(InventoryDB.getFoodTypes()).build();
    }
}
