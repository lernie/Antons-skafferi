package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.OrderStatus;
import se.miun.antonsskafferi.dao.OrderStatusDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderStatusDaoJdbc implements OrderStatusDao{
    @Override
    public List<OrderStatus> getAll() {
        java.util.List<OrderStatus> orderStatusList = new java.util.ArrayList();

        try {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT ID, NAME FROM ORDERSTATUS ");
            while (results.next()) {
                OrderStatus tempOrderStatus = new OrderStatus();
                tempOrderStatus.setId(results.getInt(1));
                tempOrderStatus.setName(results.getString(2));


                orderStatusList.add(tempOrderStatus);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return orderStatusList;
    }
}
