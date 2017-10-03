package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class Requests {

    @GET
    @Path("/diningtable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiningTables(){
        return Response.ok(ApplicationDB.getAllDiningTables()).build();
    }
}
