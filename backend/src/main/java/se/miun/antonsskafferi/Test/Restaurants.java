package se.miun.antonsskafferi.Test;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.ARestaurant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restaurants
{

  private static String tableName = "restaurants";
  private static Statement stmt = null;

  
  private static void insertRestaurants(int id, String restName, String cityName)
  {
    try
    {
      stmt = ConnectionSetup.conn.createStatement();
      stmt.execute("insert into " + tableName + " values (" + id + ",'" + restName + "','" + cityName + "')");
      
      stmt.close();
    }
    catch (SQLException sqlExcept)
    {
      sqlExcept.printStackTrace();
    }
  }
  
  public String getLastRestaurant() {
    String finalMessage = "";
    try {
      stmt = ConnectionSetup.conn.createStatement();
      ResultSet results = stmt.executeQuery("select * from " + tableName);
      java.sql.ResultSetMetaData rsmd = results.getMetaData();
      results.next();
      int id = results.getInt(1);
      String restName = results.getString(2);
      String cityName = results.getString(3);
      finalMessage = id + " " + restName + " " + cityName;

    }
    catch (SQLException sqlExcept)
    {
      sqlExcept.printStackTrace();
    }

    return finalMessage;
  }
  
  private static void selectRestaurants()
  {
    try
    {
      stmt = ConnectionSetup.conn.createStatement();
      ResultSet results = stmt.executeQuery("select * from " + tableName);
      java.sql.ResultSetMetaData rsmd = results.getMetaData();
      int numberCols = rsmd.getColumnCount();
      for (int i = 1; i <= numberCols; i++)
      {

        System.out.print(rsmd.getColumnLabel(i) + "\t\t");
      }
      
      System.out.println("\n-------------------------------------------------");
      
      while (results.next())
      {
        int id = results.getInt(1);
        String restName = results.getString(2);
        String cityName = results.getString(3);
        System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
      }
      results.close();
      stmt.close();
    }
    catch (SQLException sqlExcept)
    {
      sqlExcept.printStackTrace();
    }
  }
  
  public static java.util.List<ARestaurant> getAllRestaurants() {
    java.util.List<ARestaurant> restaurants = new java.util.ArrayList();

    try
    {
      stmt = ConnectionSetup.conn.createStatement();
      ResultSet results = stmt.executeQuery("select * from " + tableName);
      java.sql.ResultSetMetaData rsmd = results.getMetaData();
      while (results.next())
      {
        ARestaurant tempRestaurant = new ARestaurant();
        tempRestaurant.setId(results.getInt(1));
        tempRestaurant.setRestaurantName(results.getString(2));
        tempRestaurant.setCityName(results.getString(3));
        
        restaurants.add(tempRestaurant);
      }
      results.close();
      stmt.close();
    }
    catch (SQLException sqlExcept)
    {
      sqlExcept.printStackTrace();
    }
    return restaurants;
  }
  

}