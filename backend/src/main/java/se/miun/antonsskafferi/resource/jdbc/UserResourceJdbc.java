package se.miun.antonsskafferi.resource.jdbc;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.miun.antonsskafferi.Security.Secured;

import javax.annotation.Priority;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Priorities;

@RestController
@Priority(Priorities.AUTHORIZATION)
@Path("/users")
public class UserResourceJdbc {
    @GET
    @Secured
    public @ResponseBody
    String getUsers() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
