package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.dao.jdbc.OrderStatusDaoJdbc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orderstatus")
public class OrderStatusResourceJdbc {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatus() {
        OrderStatusDaoJdbc dao = new OrderStatusDaoJdbc();

        return Response.ok(dao.getAll()).build();
    }
}
