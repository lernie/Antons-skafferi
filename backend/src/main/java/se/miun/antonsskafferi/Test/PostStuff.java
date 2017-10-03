package se.miun.antonsskafferi.Test;

import javax.ws.rs.*;

@Path("/postman")
public class PostStuff {
    @GET
    @Path("/{param}")
    public String getMsg(@PathParam("param") String msg){
        String output = "Get: " + msg;
        System.out.println(output);
        return output;
    }

    @POST
    @Path("/{param}")
    public String postStr(@PathParam("param") String msg){
        String output = "Post: " + msg;
        System.out.println(output);
        return output;
    }

    @HEAD
    @Path("/{param}")
    public String headMsg(@PathParam("param") String msg){
        String output = "Head: " + msg;
        System.out.println(output);
        return output;
    }
}
