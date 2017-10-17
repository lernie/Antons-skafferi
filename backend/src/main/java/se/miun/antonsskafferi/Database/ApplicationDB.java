package se.miun.antonsskafferi.Database;

import javassist.NotFoundException;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.FoodOrder;
import se.miun.antonsskafferi.Security.AuthenticationProvider;
import se.miun.antonsskafferi.Models.OrderStatus;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ApplicationDB {
    private static String employeeTableName = "employee";
    private static String diningTableTableName = "diningtable";
    private static String foodOrderTableName = "foodorder";
    private static Statement stmt = null;

    public static boolean addEmployee(Employee emp) throws ApplicationException {
        try {
            String sqlQuery = "INSERT INTO Employee (FIRSTNAME, LASTNAME, POSITIONID, USERNAME, PASSWORD, EMAIL, SALTKEY) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                byte[] salt = AuthenticationProvider.getSalt();

                PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
                ps.setString(1, emp.getFirstName());
                ps.setString(2, emp.getLastName());
                ps.setInt(3, emp.getPositionId());
                ps.setString(4, emp.getUserName());
                ps.setString(5, AuthenticationProvider.hashPassword(emp.getPassword(), salt));
                ps.setString(6, emp.getEmail());
                ps.setBytes(7, salt);

                ps.execute();
                ConnectionSetup.conn.commit();
                return true;
            } catch(NoSuchAlgorithmException e) {
                throw new ApplicationException(e.getMessage());
            }
        } catch (SQLException sqlExcept) {
            throw new ApplicationException(sqlExcept.getMessage());
        }
    }


    public static String validateEmployee(Employee emp) throws ApplicationException {
        try {
            String sqlQuery = "SELECT FIRSTNAME, LASTNAME, EMAIL, ID, SALTKEY, PASSWORD FROM EMPLOYEE WHERE email = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setString(1, emp.getEmail());

            ResultSet result = ps.executeQuery();

            String jwt_token = "";

            while(result.next()) {
                Employee user = new Employee();
                user.setFirstName(result.getString(1));
                user.setLastName(result.getString(2));
                user.setEmail(result.getString(3));
                user.setId(result.getInt(4));
                user.setSaltKey(result.getBytes(5));
                user.setPassword(result.getString(6));

                try {
                    String hashPassword = AuthenticationProvider.hashPassword(emp.getPassword(), user.getSaltKey());

                    if (user.getPassword().equals(hashPassword)) {
                        jwt_token = AuthenticationProvider.createJWT(Integer.toString(user.getId()), user.getFirstName() + " " + user.getLastName(), user.getEmail(), 10000000);
                        return jwt_token;
                    } else {
                        throw new ApplicationException("Email or password is incorrect");
                    }
                } catch(Exception e) {
                    throw new ApplicationException("Something went wrong with an encryption");
                }
            }

            throw new ApplicationException("User was not found");
        }catch(SQLException ex) {
            throw new ApplicationException(ex.getMessage());
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
