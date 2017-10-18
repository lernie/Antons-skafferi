package se.miun.antonsskafferi.HTTP;


import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.FoodOrder;

import se.miun.antonsskafferi.Security.AuthenticationProvider;

import se.miun.antonsskafferi.dao.EmployeeDao;
import se.miun.antonsskafferi.dao.FoodOrderDao;
import se.miun.antonsskafferi.dao.jdbc.EmployeeDaoJdbc;
import se.miun.antonsskafferi.dao.jdbc.FoodOrderDaoJdbc;
import se.miun.antonsskafferi.dao.jdbc.OrderStatusDaoJdbc;


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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Employee emp) {

        try {
            EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
            String jwt_token = dao.validate(emp);
            return Response.ok(jwt_token).build();
        } catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @Path("/employee")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(){
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }

    //Add new employee user to the database
    @Path("/employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addEmployee(Employee emp) {
        try {
            EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
            if (dao.insert(emp)){
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
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        emp.setId(id);
        if (dao.update(emp)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }

    @DELETE
    @Path("/employee/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        if (dao.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }

    @GET
    @Path("/orderstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatus() {
        OrderStatusDaoJdbc dao = new OrderStatusDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }
}
