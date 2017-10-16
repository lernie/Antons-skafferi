package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Booking;
import se.miun.antonsskafferi.dao.jdbc.BookingDaoJdbc;

import javax.ws.rs.*;
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

    @GET
    @Path("/booking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooking() {
        BookingDaoJdbc bookingDaoJdbc = new BookingDaoJdbc();
        return Response.ok(bookingDaoJdbc.get()).build();
    }

    @POST
    @Path("/booking")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBooking(
        Booking bk
    ){
        BookingDaoJdbc bookingDaoJdbc = new BookingDaoJdbc();
        return Response.ok(bookingDaoJdbc.add(bk)).build();
    }
}
