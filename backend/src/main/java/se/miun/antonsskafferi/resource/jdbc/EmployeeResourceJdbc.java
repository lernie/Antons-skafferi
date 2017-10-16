package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.dao.jdbc.EmployeeDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
public class EmployeeResourceJdbc {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(){
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }

    @POST
    public Response addEmployee(Employee emp) {
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        if (dao.insert(emp)){
            return Response.ok().build();
        }
        /*
         * https://docs.microsoft.com/en-us/rest/api/storageservices/common-rest-api-error-codes
         * http://www.restapitutorial.com/lessons/httpmethods.html
         */
        return Response.status(409).entity(new ErrorResponse(409, "Email and user must be unique.")).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee emp, @PathParam("id") int id) {
        emp.setId(id);
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        if (dao.update(emp)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
        if (dao.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(400).entity(new ErrorResponse(400, "Invalid input data.")).build();
        }
    }
}
