package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.WebsiteDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

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
}
