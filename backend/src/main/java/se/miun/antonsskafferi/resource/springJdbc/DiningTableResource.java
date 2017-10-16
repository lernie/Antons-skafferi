package se.miun.antonsskafferi.resource.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.dao.springJdbc.DiningTableDaoSpringJdbc;
import se.miun.antonsskafferi.dao.springJdbc.IngredientDaoSpringJdbc;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("diningtable")
public class DiningTableResource {

    @Autowired
    private DiningTableDaoSpringJdbc diningTableDaoSpringJdbc;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<DiningTable> getAll() {
        return diningTableDaoSpringJdbc.getAll();
    }
}
