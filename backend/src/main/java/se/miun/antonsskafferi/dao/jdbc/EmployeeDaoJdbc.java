package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Security.AuthenticationProvider;
import se.miun.antonsskafferi.dao.EmployeeDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDaoJdbc implements EmployeeDao {
    @Override
    public List<Employee> getAll() {

        java.util.List<Employee> employees = new java.util.ArrayList();

        try {
            Statement stmt = ConnectionSetup.conn.createStatement();
            ResultSet results = stmt.executeQuery("select ID,FIRSTNAME,LASTNAME,POSITIONID,USERNAME,PASSWORD,EMAIL,STARTDATE from EMPLOYEE");

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
    public boolean insert(Employee employee) {
        boolean status = true;

        try {
            String sqlQuery = "INSERT INTO Employee (FIRSTNAME, LASTNAME, POSITIONID, USERNAME, PASSWORD, EMAIL) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getPositionId());
            ps.setString(4, employee.getUserName());
            ps.setString(5, employee.getPassword());
            ps.setString(6, employee.getEmail());

            ps.execute();
            ConnectionSetup.conn.commit();
        } catch (SQLException sqlExcept) {
            //sqlExcept.printStackTrace();
            status = false;
        }

        return status;
    }

    @Override
    public boolean delete(int id) {
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

    @Override
    public String validate(Employee employee) {
        try {
            String sqlQuery = "SELECT FIRSTNAME, LASTNAME, EMAIL, ID FROM EMPLOYEE WHERE email = ? AND password = ?";
            PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlQuery);

            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());

            ResultSet result = ps.executeQuery();

            String jwt_token = "";

            if (!result.next()) {
                return "No user found";
            }

            Employee user = new Employee();
            user.setFirstName(result.getString(1));
            user.setLastName(result.getString(2));
            user.setEmail(result.getString(3));
            user.setId(result.getInt(4));

            jwt_token = AuthenticationProvider.createJWT(Integer.toString(user.getId()), user.getFirstName() + " " + user.getLastName(), user.getEmail(), 10000000);
            return jwt_token;
        }catch(SQLException ex) {
            return "error";
        }
    }

    @Override
    public boolean update(Employee employee) {
        boolean status = true;

        if (employee.isValidFirstName()) {
            try {
                StringBuilder sqlString = new StringBuilder("UPDATE EMPLOYEE SET ");
                List<String> sqlUpdateList = new java.util.ArrayList();

                if (employee.isValidFirstName()) sqlUpdateList.add("FIRSTNAME=?");

                sqlString.append(String.join(",", sqlUpdateList));
                sqlString.append(" WHERE id=?");

                PreparedStatement ps = ConnectionSetup.conn.prepareStatement(sqlString.toString());

                int count = 1;

                if (employee.isValidFirstName()) {
                    ps.setString(count, employee.getFirstName());
                    count++;
                }

                ps.setInt(count,employee.getId());
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
}
