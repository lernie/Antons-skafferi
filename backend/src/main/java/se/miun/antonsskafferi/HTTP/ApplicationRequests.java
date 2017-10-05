package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.FoodOrder;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
public class ApplicationRequests {
    @Path("/foodorder")
    @GET
    @Produces(MediaType.APPLICATION_JSON)  //http://37.139.13.250:8080/api/orders?status=1
    public Response getOrders(){
        return Response.ok(ApplicationDB.getAllFoodOrders()).build();
    }

    @Path("/foodorder")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrders(List<FoodOrder> foodOrderList) {
        return Response.ok(foodOrderList).build();
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
        if (ApplicationDB.addEmployee(emp)){
            return Response.ok().build();
        }
        /*
         * https://docs.microsoft.com/en-us/rest/api/storageservices/common-rest-api-error-codes
         * http://www.restapitutorial.com/lessons/httpmethods.html
         */
        return Response.status(409).entity(new ErrorResponse(409, "Email and user must be unique.")).build();
    }

    @POST
    @Path("/employee/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee emp, @PathParam("id") int id) {
        emp.setId(id);
        if (ApplicationDB.updateEmployee(emp)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }

    @DELETE
    @Path("/employee/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        if (ApplicationDB.deleteEmployee(id)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }
}
