package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.WebsiteDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class WebsiteRequests {

    @Path("/openinghour")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpeningHours(){

        return Response.ok(WebsiteDB.getAllOpeningHours()).build();
    }
}
