package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.dao.FoodDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FoodDaoJdbc implements FoodDao {


    @Override
    public List<Food> getAll(int type, int id) {
        java.util.List<Food> foodList = new java.util.ArrayList();

        try  {

            Statement stmt = ConnectionSetup.conn.createStatement();
            String sqlQuery = "select ID, NAME, FOODTYPEID, TIMETOCOOK, PRICE from FOOD";

            if(id >= 0){
                sqlQuery += " WHERE ID = " + id;
            } else if (type >= 0) {
                sqlQuery += " WHERE FOODTYPEID = " + type;
            }

            ResultSet results = stmt.executeQuery(sqlQuery);
            //java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                Food tempFood = new Food();
                tempFood.setId(results.getInt(1));
                tempFood.setName(results.getString(2));
                tempFood.setFoodTypeId(results.getInt(3));
                tempFood.setTimeToCook(results.getInt(4));
                tempFood.setPrice(results.getInt(5));

                foodList.add(tempFood);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return foodList;
    }

    @Override
    public boolean insert(Food food) {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO " + "FOOD" +
                    "(NAME, FOODTYPEID, TIMETOCOOK, PRICE) VALUES (?, ?, ?, ?)");

            ps.setString(1, food.getName());
            ps.setInt(2, food.getFoodTypeId());
            ps.setInt(3, food.getTimeToCook());
            ps.setInt(4, food.getPrice());

            ps.execute();
            ConnectionSetup.conn.commit();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;

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
