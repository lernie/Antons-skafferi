package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.FoodType;
import se.miun.antonsskafferi.dao.FoodOrderDao;
import se.miun.antonsskafferi.dao.FoodTypeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FoodTypeDaoJdbc implements FoodTypeDao {
    @Override
    public List<FoodType> getAll() {
        java.util.List<FoodType> foodTypeList = new java.util.ArrayList();

        try  {

            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID, NAME FROM foodtype");

            while (results.next())
            {
                FoodType tempFoodType = new FoodType();
                tempFoodType.setId(results.getInt(1));
                tempFoodType.setName(results.getString(2));

                foodTypeList.add(tempFoodType);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return foodTypeList;
    }
}
