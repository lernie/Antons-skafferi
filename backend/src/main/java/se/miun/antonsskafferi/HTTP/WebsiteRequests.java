package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Database.WebsiteDB;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.Models.InventoryItem;
import se.miun.antonsskafferi.Models.TodaysLunch;

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

        return Response.ok(WebsiteDB.getAllOpeningHours()).build();
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
