package se.miun.antonsskafferi.HTTP;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import se.miun.antonsskafferi.Database.MeasurementDB;
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
}
