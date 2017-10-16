package se.miun.antonsskafferi.HTTP;

import se.miun.antonsskafferi.Database.ApplicationDB;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.dao.FoodOrderDao;
import se.miun.antonsskafferi.dao.jdbc.FoodOrderDaoJdbc;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

        FoodOrderDaoJdbc dao = new FoodOrderDaoJdbc();
        return Response.ok(dao.getAll(foParam)).build();
    }

    @Path("/foodorder")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrders(List<FoodOrder> foList) {
        FoodOrderDaoJdbc dao = new FoodOrderDaoJdbc();
        if (dao.add(foList)){
            return Response.ok().build();
        } else {
            return Response.status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "Failed to add food orders. " +
                            "Not all food orders were valid. 'foodId', and 'diningTableId'" +
                            " is required.")).build();
        }
    }



    @Path("/foodorder/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) // ttemporary
    public Response updateFoodOrder(@PathParam("id") int id, FoodOrder foParam) {
        foParam.setId(id);
        System.out.println(foParam.toString());

        FoodOrderDaoJdbc dao = new FoodOrderDaoJdbc();

        if (!dao.checkIfExist(id)) {
            return Response.status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "Food order with specified id doesn't exist.")).build();
        } else if (!dao.update(foParam)) {
            return Response.status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "Failed to update food order with specified id")).build();
        } else {
            return Response.ok().build();
        }
        //return Response.ok(foParam.toString()).build();
    }

    @DELETE
    @Path("/foodorder/{id}")
    public Response deleteFoodOrder(@PathParam("id") int id) {
        FoodOrderDaoJdbc dao = new FoodOrderDaoJdbc();
        if (dao.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }

    @DELETE
    @Path("/foodorder")
    public Response deleteFoodOrder2(@QueryParam("foodId") int foodId,
                                     @QueryParam("table") int diningTableId,
                                     @QueryParam("count") int count) {

        FoodOrderDaoJdbc dao = new FoodOrderDaoJdbc();

        if (!dao.delete(foodId, diningTableId, count)) {
            Response.status(400)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(400, "failed")).build();
        }
        return Response.ok().build();
    }


    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Employee emp) {

        if (emp.isValidEmail()) {
            String jwt_token =  ApplicationDB.validateEmployee(emp);

            if (jwt_token == "error" || jwt_token == "No user found") {
                return Response.status(500).entity(new ErrorResponse(409, jwt_token)).build();
            }
            return Response.ok(jwt_token).build();
        }

        return Response.ok().build();
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

    @GET
    @Path("/orderstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatus() {
        return Response.ok(ApplicationDB.getAllOrderStatus()).build();
    }
}
