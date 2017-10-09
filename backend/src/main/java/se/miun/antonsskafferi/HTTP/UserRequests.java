package se.miun.antonsskafferi.HTTP;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.ext.Provider;

@RestController
@Path("/api")
public class UserRequests {

    @GET
    @Path("/users")
    public @ResponseBody
    String getUsers() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}