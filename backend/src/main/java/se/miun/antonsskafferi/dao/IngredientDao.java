package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Ingredient;

public interface IngredientDao {
    java.util.List<Ingredient> getAll();
    boolean insert(Ingredient ingredient);
}
