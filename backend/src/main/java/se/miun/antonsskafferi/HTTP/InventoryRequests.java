package se.miun.antonsskafferi.HTTP;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import se.miun.antonsskafferi.Database.IngredientDB;
import se.miun.antonsskafferi.Database.MeasurementDB;
import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.Models.Measurement;

@Path("/api")
public class InventoryRequests {

    @GET
    @Path("/measurement/getall")
    public Response getAllMeasurements() {
        return Response.ok(MeasurementDB.getAllMeasurements()).build();
    }

    @POST
    @Path("/measurement/add")
    public Response add(Measurement measurement) {
        MeasurementDB.insertMeasurement(measurement);
        return Response.ok().build();
    }

    @GET
    @Path("/ingredient/getall")
    public Response getAllIngredients() {
        return Response.ok(IngredientDB.getAllIngredients()).build();
    }

    @POST
    @Path("/ingredient/add")
    public Response addIngredient(Ingredient ingredient) {
        IngredientDB.insertIngredient(ingredient);
        return Response.ok().build();
    }
}
