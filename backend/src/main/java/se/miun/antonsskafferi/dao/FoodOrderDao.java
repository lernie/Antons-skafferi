package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.FoodOrder;

import java.util.List;
public interface FoodOrderDao {
    boolean update(FoodOrder foParam);
    List<FoodOrder> getAll(FoodOrder foParam);
    boolean add(List<FoodOrder> foList);
    boolean checkIfExist(int id);
    boolean delete(int id);
}
