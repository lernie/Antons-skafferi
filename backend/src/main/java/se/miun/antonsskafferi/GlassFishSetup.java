package se.miun.antonsskafferi;



import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import se.miun.antonsskafferi.Models.Utility.JacksonFeature;
import se.miun.antonsskafferi.Models.Utility.JacksonObjectMapperProvider;
import se.miun.antonsskafferi.Security.AuthenticationFilter;
import se.miun.antonsskafferi.Test.*;

import se.miun.antonsskafferi.dao.jdbc.InventoryItemDaoJdbc;
import se.miun.antonsskafferi.resource.jdbc.*;
import se.miun.antonsskafferi.resource.springJdbc.IngredientResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
//import org.glassfish.jersey.jackson.JacksonFeature;

//Defines the base URI for all resource URIs.

//import org.glassfish.jersey.jackson.JacksonFeature;
//@ApplicationPath("/")
//The java class declares root resource and provider classes
public class GlassFishSetup extends ResourceConfig {

    public GlassFishSetup() {
        this.property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
        //register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
        //register(com.fasterxml.jackson.core.)
        register(JacksonFeature.class); // jackson instead of moxy for json-binding
        register(JacksonObjectMapperProvider.class);
        register(ParamTest.class);
        register(HelloWorld.class);
        register(GetManyRestaurants.class);
        register(PostStuff.class);
        register(MyResource.class);

        register(AuthenticationFilter.class);

        register(DiningTableResourceJdbc.class);
        register(EmployeeResourceJdbc.class);
        register(FoodOrderResourceJdbc.class);
        register(FoodResourceJdbc.class);
        register(FoodTypeResourceJdbc.class);
        register(IngredientResource.class);
        register(InventoryItemDaoJdbc.class);
        register(LoginResourceJdbc.class);
        register(MeasurementResourceJdbc.class);
        register(OpeningHourResourceJdbc.class);
        register(OrderStatusResourceJdbc.class);
        register(TodaysLunchResourceJdbc.class);
        register(UserResourceJdbc.class);

    }

}