package se.miun.antonsskafferi;

import se.miun.antonsskafferi.HTTP.ApplicationRequests;
import se.miun.antonsskafferi.HTTP.Requests;
import se.miun.antonsskafferi.HTTP.InventoryRequests;
import se.miun.antonsskafferi.HTTP.UserRequests;
import se.miun.antonsskafferi.HTTP.WebsiteRequests;
import se.miun.antonsskafferi.Test.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class GlassFishSetup extends Application {
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        //h.add(changeDeps.class);
        h.add(ParamTest.class);
        h.add(HelloWorld.class);
        h.add(GetManyRestaurants.class);
        h.add(PostStuff.class);
        h.add(UserRequests.class);
        h.add(WebsiteRequests.class);
        h.add(MyResource.class);
        h.add(ApplicationRequests.class);
        h.add(Requests.class);
        h.add(InventoryRequests.class);
        return h;
    }
}