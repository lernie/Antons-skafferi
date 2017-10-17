package se.miun.antonsskafferi.HTTP;

import com.sun.org.apache.regexp.internal.RE;
import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Database.WebsiteDB;
import se.miun.antonsskafferi.Models.*;
import se.miun.antonsskafferi.dao.jdbc.OpeningHourDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Path("/api")
public class WebsiteRequests {

    @Path("/openinghour")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpeningHours(){
        OpeningHourDaoJdbc openingHourDaoJdbc = new OpeningHourDaoJdbc();
        return Response.ok(openingHourDaoJdbc.get()).build();
    }

    @Path("/openinghour/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOpeningHour(
        @PathParam("id") int id,
        OpeningHour op
    ){
        op.setId(id);
        OpeningHourDaoJdbc openingHourDaoJdbc = new OpeningHourDaoJdbc();
        return Response.ok(openingHourDaoJdbc.update(op)).build();
    }

    @Path("/todayslunch")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodaysLunch(
        @QueryParam("startdate") Date startdate,
        @QueryParam("enddate") Date enddate
    ) {
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //Date utilStartDate = formatter.parse(startdate);
        //Date utilEndDate = formatter.parse(enddate);
        System.out.println("startdate: " + startdate + " enddate: " + enddate);


        System.out.println(WebsiteDB.getAllTodaysLunch(startdate, enddate));
        return Response.ok(WebsiteDB.getAllTodaysLunch(startdate, enddate)).build();
    }

    @Path("/todayslunch")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTodaysLunch(TodaysLunch todaysLunch) {
        if (WebsiteDB.addTodaysLunch(todaysLunch)) {
            return Response.ok().build();
        }

        return Response.status(400).entity(new ErrorResponse(400, "Error adding.")).build();
    }

    @Path("/todayslunch/{date}/{foodid}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTodaysLunch(
        @PathParam("date") Date date,
        @PathParam("foodid") int id
    ){
        return Response.ok(WebsiteDB.deleteTodaysLunch(date, id)).build();
    }

}
