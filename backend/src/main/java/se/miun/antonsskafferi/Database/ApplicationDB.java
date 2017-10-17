package se.miun.antonsskafferi.Database;

import javassist.NotFoundException;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.Security.AuthenticationProvider;
import se.miun.antonsskafferi.Models.OrderStatus;

import java.sql.*;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class ApplicationDB {
    private static String employeeTableName = "employee";
    private static String diningTableTableName = "diningtable";
    private static String foodOrderTableName = "foodorder";
    private static Statement stmt = null;




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
