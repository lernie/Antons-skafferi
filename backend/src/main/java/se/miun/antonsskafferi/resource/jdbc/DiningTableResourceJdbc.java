package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.dao.jdbc.DiningTableDaoJdbc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/diningtable")
public class DiningTableResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiningTables(){
        DiningTableDaoJdbc dao = new DiningTableDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }
}
