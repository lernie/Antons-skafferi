package se.miun.antonsskafferi.resource.jdbc;

import se.miun.antonsskafferi.Models.ErrorResponse;
import se.miun.antonsskafferi.Models.InventoryItem;
import se.miun.antonsskafferi.dao.jdbc.InventoryItemDaoJdbc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class InventoryResourceJdbc {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(InventoryItem item) {
        InventoryItemDaoJdbc dao = new InventoryItemDaoJdbc();
        if (dao.insert(item)) {
            return Response.ok().build();
        } else {
            return Response.status(403).entity(new ErrorResponse(403,"unable to add item.")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory() {
        InventoryItemDaoJdbc dao = new InventoryItemDaoJdbc();
        return Response.ok(dao.getAll()).build();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryItem(InventoryItem item, @PathParam("id") int id) {
        item.setIngredientId(id);
        InventoryItemDaoJdbc dao = new InventoryItemDaoJdbc();
        dao.update(item);
        return Response.ok().build();
    }
}
