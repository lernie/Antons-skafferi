package se.miun.antonsskafferi.Database;


import javafx.util.Pair;
import se.miun.antonsskafferi.Models.Food;
import se.miun.antonsskafferi.Models.FoodType;
import se.miun.antonsskafferi.Models.InventoryItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryDB {
    private static String tableName = "inventoryitem";
    private static Statement stmt = null;

    public static java.util.List<InventoryItem> getInventory() {
        java.util.List<InventoryItem> inventoryList = new java.util.ArrayList();

        try
        {

            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ingredientId, amount from " + tableName);
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                InventoryItem tempItem = new InventoryItem();
                tempItem.setIngredientId(results.getInt(1));
                tempItem.setAmount(results.getInt(2));
                inventoryList.add(tempItem);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return inventoryList;
    }


    public static boolean insertInventoryItem(InventoryItem item)
    {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO " + tableName +
                    "(ingredientId, amount) VALUES (?, ?)");
            ps.setInt(1, item.getIngredientId());
            ps.setInt(2, item.getAmount());

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

    public static boolean updateInventoryItem(InventoryItem item) {
        boolean status = true;
        if (item.getAmount() >= 0) {
            try {
                StringBuilder sqlString = new StringBuilder("UPDATE " + tableName + " SET ");

                List<String> sqlUpdatesList = new java.util.ArrayList();
                if (item.getAmount() >= 0) sqlUpdatesList.add("amount=?");

                sqlString.append(String.join(",", sqlUpdatesList));
                sqlString.append(" WHERE ingredientId=?");

                PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlString.toString());

                int count = 1;

                if (item.getAmount() >=0) {
                    ps.setInt(count, item.getAmount());
                    count +=1;
                }

                ps.setInt(count, item.getIngredientId());

                ps.execute();
                ConnectionSetup.conn.commit();
            } catch (SQLException sqlExcept) {
                sqlExcept.printStackTrace();
                status = false;
            }
        }

        return status;
    }



    public static java.util.List<Food> getAllFood() {
        java.util.List<Food> foodList = new java.util.ArrayList();


        try  {

            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID, NAME, FOODTYPEID, TIMETOCOOK, PRICE from " + "FOOD");
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

    public static boolean insertFood(Food food)
    {
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



    public static java.util.List<FoodType> getAllFoodTypes() {
        java.util.List<FoodType> foodTypeList = new java.util.ArrayList();


        try  {

            stmt = ConnectionSetup.conn.createStatement();
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
