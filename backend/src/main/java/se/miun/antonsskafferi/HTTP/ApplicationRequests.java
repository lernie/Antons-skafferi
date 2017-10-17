package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.Security.AuthenticationProvider;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

@Path("/api")
public class ApplicationRequests {
    @Path("/foodorder")
    @GET
    @Produces(MediaType.APPLICATION_JSON)  //http://37.139.13.250:8080/api/orders?status=1
    public Response getOrders(@DefaultValue("-1") @QueryParam("id") int id,
                              @DefaultValue("") @QueryParam("modification") String modification,
                              @DefaultValue("-1") @QueryParam("food") int foodId,
                              @DefaultValue("-1")@QueryParam("table") int diningTableId,
                              @DefaultValue("-1") @QueryParam("status") int orderStatusId,
                              @QueryParam("ready") Timestamp ready,
                              @QueryParam("created") Timestamp created,
                              @QueryParam("delivered") Timestamp delivered){
        FoodOrder foParam = new FoodOrder();
        foParam.setId(id);
        foParam.setModification(modification);
        foParam.setFoodId(foodId);
        foParam.setDiningTableId(diningTableId);
        foParam.setOrderStatusId(orderStatusId);
        foParam.setReady(ready);
        foParam.setCreated(created);
        foParam.setDelivered(delivered);

        return Response.ok(ApplicationDB.getAllFoodOrders(foParam)).build();
    }

    @Path("/foodorder")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrders(List<FoodOrder> foList) {
        ApplicationDB.addFoodOrders(foList);

        return Response.ok().build();
    }

    @Path("/foodorder/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFoodOrder(@PathParam("id") int id, FoodOrder foParam) {
        foParam.setId(id);
        if (!ApplicationDB.checkIfFoodOrderExist(id)) {
            return Response.status(400).entity(new ErrorResponse(400, "Food order with specified id doesn't exist.")).build();
        } else if (!ApplicationDB.updateFoodOrder(foParam)) {
            return Response.status(400).entity(new ErrorResponse(400, "Failed to update food order with specified id")).build();
        } else {
            return Response.ok().build();
        }
    }

    @DELETE
    @Path("/foodorder/{id}")
    public Response deleteFoodOrder(@PathParam("id") int id) {
        if (ApplicationDB.deleteFoodOrder(id)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }


    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Employee emp) {
        try {
            String jwt_token = ApplicationDB.validateEmployee(emp);
            return Response.ok(jwt_token).build();
        } catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @Path("/employee")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(){ return Response.ok(ApplicationDB.getAllEmployees()).build(); }

    //Add new employee user to the database
    @Path("/employee")
    @POST
    public Response addEmployee(Employee emp) {
        try {
            if (ApplicationDB.addEmployee(emp)){
                return Response.ok().build();
            }
        } catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
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

    @GET
    @Path("/orderstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatus() {
        return Response.ok(ApplicationDB.getAllOrderStatus()).build();
    }
 }
