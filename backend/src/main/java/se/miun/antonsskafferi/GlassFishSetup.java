package miun.erfa1501;

import miun.erfa1501.test.ParamTest;
import miun.erfa1501.test.PostStuff;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class MyApplication extends Application {
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        //h.add(changeDeps.class);
        h.add(ParamTest.class);
        h.add(HelloWorld.class);
        h.add(GetManyRestaurants.class);
        h.add(PostStuff.class);
        h.add(UserFactory.class);
        return h;
    }
}