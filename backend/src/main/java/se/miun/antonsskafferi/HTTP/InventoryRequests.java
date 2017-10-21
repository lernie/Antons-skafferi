package se.miun.antonsskafferi.HTTP;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.istack.internal.logging.Logger;

import se.miun.antonsskafferi.Models.*;
import se.miun.antonsskafferi.dao.FoodDao;
import se.miun.antonsskafferi.dao.InventoryItemDao;
import se.miun.antonsskafferi.dao.MeasurementDao;
import se.miun.antonsskafferi.dao.jdbc.*;


@Path("/api")
public class InventoryRequests {

    @GET
    @Path("/unit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUnits() {
        UnitDaoJdbc unitDaoJdbc = new UnitDaoJdbc();
        return Response.ok(unitDaoJdbc.getAll()).build();
    }

    @POST
    @Path("/unit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Unit unit) {
        UnitDaoJdbc unitDaoJdbc = new UnitDaoJdbc();
        unitDaoJdbc.insert(unit);
        return Response.ok().build();
    }


    @DELETE
    @Path("/unit/{id}")
    public Response delUnit(@PathParam("id") int id) {
        UnitDaoJdbc unitDaoJdbc = new UnitDaoJdbc();
        if (unitDaoJdbc.delete(id)) {
            return Response.ok().build();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("/ingredient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredients(@DefaultValue("-1")@QueryParam("measurementId") int measurementId) {
        IngredientDaoJdbc dao = new IngredientDaoJdbc();
        return Response.ok(dao.getAll(measurementId)).build();
    }

    @POST
    @Path("/ingredient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(Ingredient ingredient) {
        IngredientDaoJdbc dao = new IngredientDaoJdbc();
        dao.insert(ingredient);
        return Response.ok().build();
    }

    @POST
    @Path("/inventory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(InventoryItem item) {
        InventoryItemDaoJdbc invDao = new InventoryItemDaoJdbc();
        if (invDao.insert(item)) {
            return Response.ok().build();
        } else {
            return Response.status(403).entity(new ErrorResponse(403,"unable to add item.")).build();
        }
    }

    @GET
    @Path("/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory() {
        InventoryItemDaoJdbc invDao = new InventoryItemDaoJdbc();
        return Response.ok(invDao.getAll()).build();
    }

    @POST
    @Path("/inventory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryItem(InventoryItem item, @PathParam("id") int id) {
        item.setId(id);
        InventoryItemDaoJdbc invDao = new InventoryItemDaoJdbc();
        invDao.update(item);
        return Response.ok().build();
    }

    @DELETE
    @Path("/inventory/{id}")
    public Response deleteInventoryItem(
        @PathParam("id") int id
    ){
        InventoryItemDaoJdbc inventoryItemDaoJdbc = new InventoryItemDaoJdbc();
        return Response.ok(inventoryItemDaoJdbc.delete(id)).build();
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
        InventoryItemDaoJdbc invDao = new InventoryItemDaoJdbc();
        return Response.ok(invDao.getAll()).build();
    }
}
