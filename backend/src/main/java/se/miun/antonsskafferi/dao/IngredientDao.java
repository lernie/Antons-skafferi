package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDao {
    List<Ingredient> getAll(int measurementId);
    boolean insert(Ingredient ingredient);
}
