package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.InventoryItem;
import se.miun.antonsskafferi.dao.InventoryItemDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InventoryItemDaoJdbc implements InventoryItemDao{
    @Override
    public List<InventoryItem> getAll() {
        java.util.List<InventoryItem> inventoryList = new java.util.ArrayList();

        try
        {

            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ingredientId, amount from InventoryItem");
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

    @Override
    public boolean insert(InventoryItem item) {
        boolean status = true;
        try
        {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO InventoryItem" +
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

    @Override
    public boolean update(InventoryItem item) {
        boolean status = true;
        if (item.getAmount() >= 0) {
            try {
                StringBuilder sqlString = new StringBuilder("UPDATE " + "InventoryItem" + " SET ");

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
}
