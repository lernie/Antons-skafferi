package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.Models.FoodType;

public interface FoodDao {
    boolean insert(Food food);
    java.util.List<Food> getAll(int type, int id);
    boolean delete(int id);
    boolean checkIfExist(int id);
    boolean update(Food foParam);
}
