package se.miun.antonsskafferi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserFactory {
    @GET("/get/{Id}")
    public Response getUserByUserId(UserId) {

    }

    @


    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add() {
        return Response.ok("PAJAS").build();
    }
}
