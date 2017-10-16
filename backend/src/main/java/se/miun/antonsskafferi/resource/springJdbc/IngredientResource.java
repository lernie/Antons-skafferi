package se.miun.antonsskafferi.resource.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.dao.springJdbc.IngredientDaoSpringJdbc;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("ingredient")
public class IngredientResource {

    @Autowired
    private IngredientDaoSpringJdbc ingredientDAO;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Ingredient> getAll() {
        return ingredientDAO.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody
    void insertIngredient(@RequestBody Ingredient ingredient) {
        ingredientDAO.insert(ingredient);
    }

}
