package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.Measurement;
import se.miun.antonsskafferi.dao.jdbc.MeasurementDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/measurement")
public class MeasurementResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMeasurements() {
        MeasurementDaoJdbc dao = new MeasurementDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Measurement measurement) {
        MeasurementDaoJdbc dao = new MeasurementDaoJdbc();
        dao.insert(measurement);
        //MeasurementDB.insertMeasurement(measurement);
        return Response.ok().build();
    }


    @DELETE
    public Response delMeasurement(@PathParam("id") int id) {
        MeasurementDaoJdbc dao = new MeasurementDaoJdbc();
        if (dao.delete(id)) {
            return Response.ok().build();
        }
        return Response.status(500).build();
    }
}
