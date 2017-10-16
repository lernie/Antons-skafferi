package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.dao.FoodOrderDao;
import se.miun.antonsskafferi.dao.jdbc.FoodOrderDaoJdbc;

import java.util.List;

public class FoodOrderDaoSpringJdbc implements FoodOrderDao{
    @Override
    public boolean checkIfExist(int id) {
        return false;
    }

    @Override
    public boolean update(FoodOrder foParam) {
        return false;
    }

    @Override
    public List<FoodOrder> getAll(FoodOrder foParam) {
        return null;
    }

    @Override
    public boolean insert(List<FoodOrder> foList) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
