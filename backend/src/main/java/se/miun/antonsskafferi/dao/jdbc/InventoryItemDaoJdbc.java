package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.InventoryItem;
import se.miun.antonsskafferi.dao.InventoryItemDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InventoryItemDaoJdbc implements InventoryItemDao {
    @Override
    public List<InventoryItem> getAll() {
        java.util.List<InventoryItem> inventoryList = new java.util.ArrayList();

        try {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select id, name, unitid, instock from Inventory  ");
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next()) {
                InventoryItem tempItem = new InventoryItem();
                tempItem.setId(results.getInt(1));
                tempItem.setName(results.getString(2));
                tempItem.setUnitid(results.getInt(3));
                tempItem.setInstock(results.getInt(4));
                inventoryList.add(tempItem);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return inventoryList;
    }

    @Override
    public boolean insert(InventoryItem inventoryItem) {
        boolean status = true;
        try {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("INSERT INTO Inventory"+
                    "(name, unitid, instock) VALUES (?, ?, ?)");
            ps.setString(1, inventoryItem.getName());
            ps.setInt(2, inventoryItem.getUnitid());
            ps.setInt(3, inventoryItem.getInstock());

            ps.execute();
            ConnectionSetup.conn.commit();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public boolean update(InventoryItem inventoryItem) {
        boolean status = true;
        if (inventoryItem.getInstock() >= 0) {
            try {
                StringBuilder sqlString = new StringBuilder("UPDATE InventoryItem SET ");

                List<String> sqlUpdatesList = new java.util.ArrayList();
                if (inventoryItem.getInstock() >= 0) sqlUpdatesList.add("instock=?");

                sqlString.append(String.join(",", sqlUpdatesList));
                sqlString.append(" WHERE id=?");

                PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlString.toString());

                int count = 1;

                if (inventoryItem.getInstock() >=0) {
                    ps.setInt(count, inventoryItem.getInstock());
                    count +=1;
                }

                ps.setInt(count, inventoryItem.getId());

                ps.execute();
                ConnectionSetup.conn.commit();
            } catch (SQLException sqlExcept) {
                sqlExcept.printStackTrace();
                status = false;
            }
        }

        return status;
    }

    @Override
    public boolean delete(int id) {
        boolean status = true;

        try {
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement("DELETE FROM Inventory WHERE id=?");
            ps.setInt(1, id);

            ps.execute();
            ConnectionSetup.conn.commit();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            status=false;
        }

        return status;
    }
}
