package se.miun.antonsskafferi.dao.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.miun.antonsskafferi.Models.Ingredient;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import se.miun.antonsskafferi.dao.IngredientDao;

@Controller
public class IngredientDaoSpringJdbc implements IngredientDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> ingredientList = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT ID, NAME, MEASUREMENTID FROM INGREDIENT");

        for (Map row : rows) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId((Integer)(row.get("ID")));
            ingredient.setName((String)(row.get("NAME")));
            ingredient.setMeasurementId((Integer)(row.get("MEASUREMENTID")));
            ingredientList.add(ingredient);
        }

        return ingredientList;
    }

    @Override
    public boolean insert(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredient (NAME, MEASUREMENTID) VALUES (?, ?)";

        Object[] params = new Object[] {
                ingredient.getName(), ingredient.getMeasurementId()
        };

        int[] types = new int[] {
                Types.VARCHAR,
                Types.INTEGER
        };

        jdbcTemplate.update(sql, params, types);

        return true;
    }
}
