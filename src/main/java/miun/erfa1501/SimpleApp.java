package miun.erfa1501;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Restaurants
{
  private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true";
  private static String tableName = "restaurants";
  
  private static java.sql.Connection conn = null;
  private static Statement stmt = null;

  private static void createConnection()
  {
    try
    {
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
      
      conn = java.sql.DriverManager.getConnection(dbURL);
    }
    catch (Exception except)
    {
      except.printStackTrace();
    }
  }
  
  private static void insertRestaurants(int id, String restName, String cityName)
  {
    try
    {
      stmt = conn.createStatement();
      stmt.execute("insert into " + tableName + " values (" + id + ",'" + restName + "','" + cityName + "')");
      
      stmt.close();
    }
    catch (SQLException sqlExcept)
    {
      sqlExcept.printStackTrace();
    }
  }
  
  public String getLastRestaurant() {
    createConnection();
    String finalMessage = "";
    try {
      stmt = conn.createStatement();
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
    shutdown();
    return finalMessage;
  }
  
  private static void selectRestaurants()
  {
    try
    {
      stmt = conn.createStatement();
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
  
  public java.util.List<ARestaurant> getAllRestaurants() {
    java.util.List<ARestaurant> restaurants = new java.util.ArrayList();
    createConnection();
    try
    {
      stmt = conn.createStatement();
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
    shutdown();
    return restaurants;
  }
  
  private static void shutdown()
  {
    try
    {
      if (stmt != null)
      {
        stmt.close();
      }
      if (conn != null)
      {
        java.sql.DriverManager.getConnection(dbURL + ";shutdown=true");
        conn.close();
      }
    }
    catch (SQLException localSQLException) {}
  }
}