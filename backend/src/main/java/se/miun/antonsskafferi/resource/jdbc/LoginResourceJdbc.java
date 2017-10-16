package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.dao.jdbc.EmployeeDaoJdbc;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResourceJdbc {
    @POST
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Employee emp) {

       // if (emp.isValidEmail()) {
            //EmployeeDaoJdbc dao = new EmployeeDaoJdbc();
            //String jwt_token = dao.validate(emp);

           // if (jwt_token == "error" || jwt_token == "No user found") {
           //     return Response.status(500).entity(new ErrorResponse(409, jwt_token)).build();
            //}
            //return Response.ok(jwt_token).build();
      //  }

        return Response.ok().build();
    }
}
