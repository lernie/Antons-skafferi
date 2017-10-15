package se.miun.antonsskafferi.Database;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.Security.AuthenticationProvider;
import se.miun.antonsskafferi.Models.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class ApplicationDB {
    private static String employeeTableName = "employee";
    private static String diningTableTableName = "diningtable";
    private static String foodOrderTableName = "foodorder";
    private static Statement stmt = null;

    public static java.util.List<Employee> getAllEmployees() {
        java.util.List<Employee> employees = new java.util.ArrayList();

        try {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID,FIRSTNAME,LASTNAME,POSITIONID,USERNAME,PASSWORD,EMAIL,STARTDATE from " + employeeTableName);

            while (results.next()) {
                Employee tempEmployee = new Employee();
                tempEmployee.setId(results.getInt(1));
                tempEmployee.setFirstName(results.getString(2));
                tempEmployee.setLastName(results.getString(3));
                tempEmployee.setPositionId(results.getInt(4));
                tempEmployee.setUserName(results.getString(5));
                tempEmployee.setPassword(results.getString(6));
                tempEmployee.setEmail(results.getString(7));
                tempEmployee.setStartDate(results.getDate(8));

                employees.add(tempEmployee);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return employees;
    }

    public static boolean addEmployee(Employee emp) {
        boolean status = true;

        try {
            String sqlQuery = "INSERT INTO Employee (FIRSTNAME, LASTNAME, POSITIONID, USERNAME, PASSWORD, EMAIL) "
                            + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setInt(3, emp.getPositionId());
            ps.setString(4, emp.getUserName());
            ps.setString(5, AuthenticationProvider.hashPassword(emp.getPassword()));
            ps.setString(6, emp.getEmail());

            ps.execute();
            ConnectionSetup.conn.commit();
        } catch (SQLException sqlExcept) {
            //sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }

    public static String validateEmployee(Employee emp) {
        try {
            String sqlQuery = "SELECT FIRSTNAME, LASTNAME, EMAIL, ID FROM EMPLOYEE WHERE email = ? AND password = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setString(1, emp.getEmail());
            ps.setString(2, emp.getPassword());

            ResultSet result = ps.executeQuery();

            String jwt_token = "";

            while(result.next()) {
                Employee user = new Employee();
                user.setFirstName(result.getString(1));
                user.setLastName(result.getString(2));
                user.setEmail(result.getString(3));
                user.setId(result.getInt(4));

                jwt_token = AuthenticationProvider.createJWT(Integer.toString(user.getId()), user.getFirstName() + " " + user.getLastName(), user.getEmail(), 10000000);
                return jwt_token;
            }

            return "No user found";
        }catch(SQLException ex) {
            return "error";
        }
    }

    public static boolean updateEmployee(Employee emp) {
        boolean status = true;

        if (emp.isValidFirstName()) {
            try {
                StringBuilder sqlString = new StringBuilder("UPDATE EMPLOYEE SET ");
                List<String> sqlUpdateList = new java.util.ArrayList();

                if (emp.isValidFirstName()) sqlUpdateList.add("FIRSTNAME=?");

                sqlString.append(String.join(",", sqlUpdateList));
                sqlString.append(" WHERE id=?");

                PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlString.toString());

                int count = 1;

                if (emp.isValidFirstName()) {
                    ps.setString(count, emp.getFirstName());
                    count++;
                }

                ps.setInt(count,emp.getId());
                ps.execute();
                ConnectionSetup.conn.commit();

            } catch (SQLException sqlExcept) {
                sqlExcept.printStackTrace();
                status = false;
            }
        } else {
            status = false;
        }

        return status;
    }

    public static boolean checkIfFoodOrderExist(int id) {
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

    public static boolean deleteFoodOrder(int id) {
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


    public static boolean deleteEmployee(int id) {
        boolean status = true;

        try {
            String sqlQuery = "DELETE FROM EMPLOYEE WHERE Id = ?";
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

    public static java.util.List<DiningTable> getAllDiningTables() {
        java.util.List<DiningTable> diningTables = new java.util.ArrayList();

        try {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT ID, NAME FROM " + diningTableTableName);
            while (results.next()) {
                DiningTable tempDiningTable = new DiningTable();
                tempDiningTable.setId(results.getInt(1));
                tempDiningTable.setName(results.getString(2));

                diningTables.add(tempDiningTable);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return diningTables;
    }

    public static java.util.List<FoodOrder> getAllFoodOrders(FoodOrder foParam) {
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



    public static boolean updateFoodOrder(FoodOrder foParam) {
        boolean status = true;

        try {
            String sqlQuery = "UPDATE FOODORDER " +
                    "SET modification = ?, " +
                    "foodId = ?, " +
                    "diningTableId = ?, " +
                    "orderStatusId = ?, " +
                    "ready = ?, " +
                    "delivered = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            System.out.println("ID: " + foParam.getId() + " foodID: " + foParam.getFoodId() + " diningTableId: " + foParam.getDiningTableId()
                    + " orderStatusId: " + foParam.getOrderStatusId());


            ps.setString(1, foParam.getModification());
            ps.setInt(2, foParam.getFoodId());
            ps.setInt(3, foParam.getDiningTableId());
            ps.setInt(4, foParam.getOrderStatusId());
            ps.setTimestamp(5,foParam.getReady());
            ps.setTimestamp(6,foParam.getDelivered());
            ps.setInt(7, foParam.getId());

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



    public static boolean addFoodOrders(List<FoodOrder> foList) {
        boolean status = true;
        try {
            ConnectionSetup.conn.setAutoCommit(false);
            String sqlQuery = "INSERT INTO FOODORDER(FOODID, MODIFICATION, DININGTABLEID, ORDERSTATUSID) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            for (FoodOrder fo : foList) {
                ps.setInt(1,fo.getFoodId());
                ps.setString(2, fo.getModification());
                ps.setInt(3, fo.getDiningTableId());
                ps.setInt(4,fo.getOrderStatusId());
                ps.addBatch();
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



    public static java.util.List<OrderStatus> getAllOrderStatus() {
        java.util.List<OrderStatus> orderStatusList = new java.util.ArrayList();

        try {
            stmt = ConnectionSetup.conn.createStatement();
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
