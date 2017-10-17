package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.Models.FoodType;
import se.miun.antonsskafferi.dao.FoodDao;

import java.sql.*;
import java.util.List;

public class FoodDaoJdbc implements FoodDao {

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
    public boolean delete(int id) {
        boolean status = true;

        try {
            String sqlQuery = "DELETE FROM FOOD WHERE Id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setInt(1,id);
            ps.execute();
            ConnectionSetup.conn.commit();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }

    @Override
    public boolean checkIfExist(int id) {
        boolean status = true;

        try {
            String sqlQuery = "SELECT COUNT(1) FROM FOOD WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            ConnectionSetup.conn.commit();

            rs.next();
            if (rs.getInt(1) == 0) {
                status = false;
            }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }

    @Override
    public boolean update(Food foParam) {
        boolean status = true;

        try {
            String sqlQuery = "UPDATE FOOD " +
                    "SET name = CASE WHEN ? = '' THEN  name ELSE ? END, " +
                    "foodTypeId = CASE WHEN ? = -1 THEN foodTypeId ELSE ? END, " +
                    "timeToCook = CASE WHEN ? = -1 THEN timeToCook ELSE ? END, " +
                    "price = CASE WHEN ? = -1 THEN price ELSE ? END " +
                    "WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            // (foParam.getModification() == null)

            ps.setString(1, foParam.getName() == null ? "" : foParam.getName());
            ps.setString(2, foParam.getName() == null ? "" : foParam.getName());
            ps.setInt(3, foParam.getFoodTypeId());
            ps.setInt(4, foParam.getFoodTypeId());
            ps.setInt(5, foParam.getTimeToCook());
            ps.setInt(6, foParam.getTimeToCook());
            ps.setInt(7, foParam.getPrice());
            ps.setInt(8, foParam.getPrice());
            ps.setInt(9, foParam.getId());

            ps.execute();
            //ResultSet results = ps.executeQuery();
            ps.close();
            //results.close();
            ConnectionSetup.conn.commit();
            //stmt.close();
        } catch (SQLException sqlExcept) {
            status = false;
            sqlExcept.printStackTrace();

        }
        return status;
    }
}
