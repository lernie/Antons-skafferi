package se.miun.antonsskafferi.HTTP;

import com.sun.org.apache.regexp.internal.RE;
import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Booking;
import se.miun.antonsskafferi.Models.ErrorResponse;
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
    public Response addBooking(Booking bk ){

        BookingDaoJdbc bookingDaoJdbc = new BookingDaoJdbc();
        if (!bookingDaoJdbc.checkIfLess(7, bk.getBookingDate())) {
            return Response
                    .status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "Can't add another booking on selected day."))
                    .build();
        }

        if (!bookingDaoJdbc.add(bk)) {
            return Response
                    .status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "Error adding booking."))
                    .build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/booking/{id}")
    public Response deleteBooking(
        @PathParam("id") int id
    ){
        BookingDaoJdbc bookingDaoJdbc = new BookingDaoJdbc();
        if(!bookingDaoJdbc.exists(id)){
            return Response
                    .status(404)
                    .build();
        }
        if(!bookingDaoJdbc.delete(id)){
            return Response
                    .status(400)
                    .build();
        }
        return Response.ok().build();
    }
}
