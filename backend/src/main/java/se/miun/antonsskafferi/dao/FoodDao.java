package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Food;

public interface FoodDao {
    java.util.List<Food> getAll(int type, int id);
    boolean insert(Food food);
    boolean delete(int id);
    boolean update(int id);
}
