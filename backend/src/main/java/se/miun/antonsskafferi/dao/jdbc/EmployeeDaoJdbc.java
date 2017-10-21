package se.miun.antonsskafferi.dao.jdbc;

import se.miun.antonsskafferi.Database.ApplicationException;
import se.miun.antonsskafferi.Database.ConnectionSetup;
import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.Security.AuthenticationProvider;
import se.miun.antonsskafferi.dao.EmployeeDao;

import java.security.NoSuchAlgorithmException;
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
    public boolean insert(Employee emp) throws ApplicationException {
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

    @Override
    public String validate(Employee emp) throws ApplicationException {
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
                    throw new ApplicationException("Could not hash the password");
                }
            }

            throw new ApplicationException("User was not found");
        }catch(SQLException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @Override
    public boolean update(Employee emp) {

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
}
