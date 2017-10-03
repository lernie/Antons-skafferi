package se.miun.antonsskafferi.Database;

import se.miun.antonsskafferi.Models.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IngredientDB {
    private static String tableName = "ingredient";
    private static Statement stmt = null;

    public static java.util.List<Ingredient> getAllIngredients() {
        java.util.List<Ingredient> ingredients = new java.util.ArrayList();

        try
        {

            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                Ingredient tempIngredient = new Ingredient();
                tempIngredient.setId(results.getInt(1));
                tempIngredient.setName(results.getString(2));

                ingredients.add(tempIngredient);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return ingredients;
    }


    public static boolean insertIngredient(Ingredient ingredient)
    {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO " + tableName + "(name) VALUES (?)");
            ps.setString(1, ingredient.getName());

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
}
