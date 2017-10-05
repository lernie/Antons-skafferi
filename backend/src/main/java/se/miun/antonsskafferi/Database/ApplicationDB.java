package se.miun.antonsskafferi.Database;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Models.DiningTable;
import se.miun.antonsskafferi.Models.FoodOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationDB {
    private static String employeeTableName = "employee";
    private static String diningTableTableName = "diningtable";
    private static String foodOrderTableName = "foodorder";
    private static Statement stmt = null;

    public static java.util.List<Employee> getAllEmployees() {
        java.util.List<Employee> employees = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID,FIRSTNAME,LASTNAME,POSITIONID,USERNAME,PASSWORD,EMAIL,STARTDATE from " + employeeTableName);
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
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
        }
        catch (SQLException sqlExcept)
        {
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
            ps.setString(5, emp.getPassword());
            ps.setString(6, emp.getEmail());

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

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + diningTableTableName);
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                DiningTable tempDiningTable = new DiningTable();
                tempDiningTable.setId(results.getInt(1));
                tempDiningTable.setName(results.getString(2));

                diningTables.add(tempDiningTable);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return diningTables;
    }

    public static java.util.List<FoodOrder> getAllFoodOrders() {
        java.util.List<FoodOrder> foodOrders = new java.util.ArrayList();

        try
        {
            stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + foodOrderTableName);
            java.sql.ResultSetMetaData rsmd = results.getMetaData();
            while (results.next())
            {
                FoodOrder tempFoodOrder = new FoodOrder();
                tempFoodOrder.setId(results.getInt(1));
                tempFoodOrder.setFoodId(results.getInt(2));
                tempFoodOrder.setModification(results.getString(3));
                tempFoodOrder.setDiningTableOrderId(results.getInt(4));
                tempFoodOrder.setOrderStatusId(results.getInt(5));
                tempFoodOrder.setReady(results.getTimestamp(6));
                tempFoodOrder.setCreated(results.getTimestamp(7));
                tempFoodOrder.setDelivered(results.getTimestamp(8));

                foodOrders.add(tempFoodOrder);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return foodOrders;
    }
}
