package se.miun.antonsskafferi;

import org.glassfish.jersey.jackson.JacksonFeature;
import se.miun.antonsskafferi.HTTP.ApplicationRequests;
import se.miun.antonsskafferi.HTTP.Requests;
import se.miun.antonsskafferi.HTTP.InventoryRequests;
import se.miun.antonsskafferi.HTTP.UserRequests;
import se.miun.antonsskafferi.HTTP.WebsiteRequests;
import se.miun.antonsskafferi.Models.Utility.JacksonObjectMapperProvider;
import se.miun.antonsskafferi.Security.AuthFilter;
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


    //http://jmchung.github.io/blog/2014/06/18/how-to-customise-the-jackson-json-objectmapper-in-java-ee-enterprise-application/
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(JacksonFeature.class); // jackson instead of moxy for json-binding
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
        h.add(UserRequests.class);
        h.add(InventoryRequests.class);
        /*http://jmchung.github.io/blog/2014/06/18/how-to-customise-the-jackson-json-objectmapper-in-java-ee-enterprise-application/*/
        h.add(JacksonObjectMapperProvider.class);
        //h.add(AuthFilter.class);
        return h;
    }
}