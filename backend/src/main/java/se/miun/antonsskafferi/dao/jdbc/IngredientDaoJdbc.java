package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.Ingredient;
import se.miun.antonsskafferi.dao.IngredientDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IngredientDaoJdbc implements IngredientDao {
    private static String tableName = "ingredient";
    private static Statement stmt = null;

    @Override
    public java.util.List<Ingredient> getAll() {
        java.util.List<Ingredient> ingredients = new java.util.ArrayList();

        try
        {
            String sqlQuery = "select Id, Name, measurementId from " + tableName;
//            if (measurementId >= 0) {
//                sqlQuery += " WHERE measurementId = ?";
//            }
//
          PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
//
//            if (measurementId >= 0) {
//                ps.setInt(1,measurementId);
//            }

            ResultSet results = ps.executeQuery();
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                Ingredient tempIngredient = new Ingredient();
                tempIngredient.setId(results.getInt(1));
                tempIngredient.setName(results.getString(2));
                tempIngredient.setMeasurementId(results.getInt(3));

                ingredients.add(tempIngredient);
            }
            results.close();
            ps.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public boolean insert(Ingredient ingredient)
    {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO " + tableName + "(name, measurementId) VALUES (?, ?)");
            ps.setString(1, ingredient.getName());
            ps.setInt(2, ingredient.getMeasurementId());
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
