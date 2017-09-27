package miun.erfa1501;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {

    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream viewHome(){
        System.out.println("hmm");
        InputStream html = getClass().getClassLoader().getResourceAsStream("home2.html");
        return html;
    }
}