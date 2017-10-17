package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.dao.EmployeeDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDaoJdbc implements EmployeeDao{
    @Override
    public List<Employee> getAll() {
        java.util.List<Employee> employees = new java.util.ArrayList();

        try {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID,FIRSTNAME,LASTNAME,POSITIONID,USERNAME,PASSWORD,EMAIL,STARTDATE FROM EMPLOYEE");

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

    @Override
    public boolean insert(Employee emp) {
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
            //sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }

    @Override
    public String validate(Employee emp) throws Exception {
        return "";
    }

    @Override
    public boolean update(Employee emp) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
