package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.dao.FoodOrderDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FoodOrderDaoJdbc implements FoodOrderDao {

    @Override
    public boolean update(FoodOrder foParam) {
        boolean status = true;

        try {
            String sqlQuery = "UPDATE FOODORDER " +
                    "SET modification = CASE WHEN ? = '' THEN  modification ELSE ? END, " +
                    "foodId = CASE WHEN ? = -1 THEN foodId ELSE ? END, " +
                    "diningTableId = CASE WHEN ? = -1 THEN diningTableId ELSE ? END, " +
                    "orderStatusId = CASE WHEN ? = -1 THEN orderStatusId ELSE ? END, " +
                    "ready = CASE WHEN ? = TIMESTAMP('1970-01-01-00.00.00.000000') THEN ready ELSE ? END, " +
                    "delivered = CASE WHEN ? = TIMESTAMP('1970-01-01-00.00.00.000000') THEN delivered ELSE ? END " +
                    "WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            System.out.println("ID: " + foParam.getId() + " foodID: " + foParam.getFoodId() + " diningTableId: " + foParam.getDiningTableId()
                    + " orderStatusId: " + foParam.getOrderStatusId());

            // (foParam.getModification() == null)

            ps.setString(1, foParam.getModification() == null ? "" : foParam.getModification());
            ps.setString(2, foParam.getModification() == null ? "" : foParam.getModification());
            ps.setInt(3, foParam.getFoodId());
            ps.setInt(4, foParam.getFoodId());
            ps.setInt(5, foParam.getDiningTableId());
            ps.setInt(6, foParam.getDiningTableId());
            ps.setInt(7, foParam.getOrderStatusId());
            ps.setInt(8, foParam.getOrderStatusId());
            ps.setTimestamp(9,foParam.getReady() == null ? new Timestamp(0) : foParam.getReady());
            ps.setTimestamp(10,foParam.getReady() == null ? new Timestamp(0) : foParam.getReady());
            ps.setTimestamp(11,foParam.getDelivered() == null ? new Timestamp(0) : foParam.getDelivered());
            ps.setTimestamp(12,foParam.getDelivered() == null ? new Timestamp(0) : foParam.getDelivered());
            ps.setInt(13, foParam.getId());

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

    @Override
    public List<FoodOrder> getAll(FoodOrder foParam) {
        java.util.List<FoodOrder> foodOrders = new java.util.ArrayList<FoodOrder>();

        try {
            String sqlQuery = "SELECT ID, FOODID, MODIFICATION, DININGTABLEID, " +
                    "ORDERSTATUSID, CREATED, READY, DELIVERED FROM FOODORDER WHERE " +
                    "(? = -1 OR Id = ?) AND " +
                    "(? = '' OR modification = ? ) AND " +
                    "(? = -1 OR foodId = ? ) AND " +
                    "(? = -1 OR diningTableId = ?) AND " +
                    "(? = -1 OR orderStatusId = ?) ";/*AND " +
                    "(? != NULL || ready = ?) AND " +
                    "(? != NULL || created = ? ) AND " +
                    "(? != NULL || delivered = ?)";*/
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            System.out.println("ID: " + foParam.getId() + " foodID: " + foParam.getFoodId() + " diningTableId: " + foParam.getDiningTableId()
                    + " orderStatusId: " + foParam.getOrderStatusId());

            ps.setInt(1, foParam.getId());
            ps.setInt(2, foParam.getId());
            ps.setString(3, foParam.getModification());
            ps.setString(4, foParam.getModification());
            ps.setInt(5, foParam.getFoodId());
            ps.setInt(6, foParam.getFoodId());
            ps.setInt(7, foParam.getDiningTableId());
            ps.setInt(8, foParam.getDiningTableId());
            ps.setInt(9, foParam.getOrderStatusId());
            ps.setInt(10, foParam.getOrderStatusId());/*
            ps.setTimestamp(11,foParam.getReady());
            ps.setTimestamp(12,foParam.getReady());
            ps.setTimestamp(13,foParam.getCreated());
            ps.setTimestamp(14,foParam.getCreated());
            ps.setTimestamp(15,foParam.getDelivered());
            ps.setTimestamp(16,foParam.getDelivered());*/

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                FoodOrder tempFoodOrder = new FoodOrder();
                tempFoodOrder.setId(results.getInt(1));
                tempFoodOrder.setFoodId(results.getInt(2));
                tempFoodOrder.setModification(results.getString(3));
                tempFoodOrder.setDiningTableId(results.getInt(4));
                tempFoodOrder.setOrderStatusId(results.getInt(5));
                tempFoodOrder.setCreated(results.getTimestamp(6));
                tempFoodOrder.setReady(results.getTimestamp(7));

                tempFoodOrder.setDelivered(results.getTimestamp(8));

                foodOrders.add(tempFoodOrder);
            }
            results.close();
            ConnectionSetup.conn.commit();
            //stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return foodOrders;
    }

    @Override
    public boolean add(List<FoodOrder> foList) {
        boolean status = true;
        try {
            ConnectionSetup.conn.setAutoCommit(false);
            String sqlQuery = "INSERT INTO FOODORDER(FOODID, MODIFICATION, DININGTABLEID, ORDERSTATUSID) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            for (FoodOrder fo : foList) {
                if (fo.isValid()) {
                    ps.setInt(1,fo.getFoodId());
                    ps.setString(2, fo.getModification());
                    ps.setInt(3, fo.getDiningTableId());
                    ps.setInt(4,fo.getOrderStatusId() == -1 ? 0 : fo.getOrderStatusId());
                    ps.addBatch();
                } else {
                    status = false;
                    break;
                }
            }
            if (!status) {
                ps.close();
                return status;
            }

            ps.executeBatch();
            ConnectionSetup.conn.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            status = false;
        } finally {
            try {
                ConnectionSetup.conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    @Override
    public boolean checkIfExist(int id) {
        boolean status = true;

        try {
            String sqlQuery = "SELECT COUNT(1) FROM foodorder WHERE id = ?";
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
    public boolean delete(int id) {
        boolean status = true;

        try {
            String sqlQuery = "DELETE FROM FOODORDER WHERE Id = ?";
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
}
