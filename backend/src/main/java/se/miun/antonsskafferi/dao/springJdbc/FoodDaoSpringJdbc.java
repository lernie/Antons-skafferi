package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.dao.FoodDao;

import java.util.List;

public class FoodDaoSpringJdbc implements FoodDao {

    @Override
    public List<Food> getAll(int type, int id) {
        return null;
    }

    @Override
    public boolean insert(Food food) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(int id) {
        return false;
    }
}
