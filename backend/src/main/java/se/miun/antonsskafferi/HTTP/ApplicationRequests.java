package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class ApplicationRequests {
    @Path("/foodorder")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders( //http://37.139.13.250:8080/api/orders?status=1
            ){

        return Response.ok(ApplicationDB.getAllFoodOrders()).build();
    }

    @Path("/employee")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(){
        return Response.ok(ApplicationDB.getAllEmployees()).build();
    }

    @Path("/employee")
    @POST
    public Response addEmployee(Employee emp) {
        ApplicationDB.addEmployee(emp);
        return Response.ok().build();
    }
}
