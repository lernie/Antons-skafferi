package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.TodaysLunch;
import se.miun.antonsskafferi.dao.jdbc.TodaysLunchDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

@Path("/todayslunch")
public class TodaysLunchResourceJdbc {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodaysLunch(
            @QueryParam("startdate") Date startdate,
            @QueryParam("enddate") Date enddate) {
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //Date utilStartDate = formatter.parse(startdate);
        //Date utilEndDate = formatter.parse(enddate);
        System.out.println("startdate: " + startdate + " enddate: " + enddate);

        TodaysLunchDaoJdbc dao = new TodaysLunchDaoJdbc();
        System.out.println(dao.getAll(startdate, enddate));
        return Response.ok(dao.getAll(startdate, enddate)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTodaysLunch(TodaysLunch todaysLunch) {
        TodaysLunchDaoJdbc dao = new TodaysLunchDaoJdbc();

        if (dao.insert(todaysLunch)) {
            return Response.ok().build();
        }

        return Response.status(400).entity(new ErrorResponse(400, "Error adding.")).build();
    }

}
