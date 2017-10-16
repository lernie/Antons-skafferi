package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.FoodOrder;

import java.util.List;

public interface FoodOrderDao {
    boolean checkIfExist(int id);
    boolean update(FoodOrder foParam);
    java.util.List<FoodOrder> getAll(FoodOrder foParam);
    boolean insert(List<FoodOrder> foList);
    boolean delete(int id);
}
