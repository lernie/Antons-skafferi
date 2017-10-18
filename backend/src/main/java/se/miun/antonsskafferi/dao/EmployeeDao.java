package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Database.ApplicationException;
import se.miun.antonsskafferi.Models.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    boolean insert(Employee emp) throws ApplicationException;
    String validate(Employee emp) throws ApplicationException;
    boolean update(Employee emp);
    boolean delete(int id);
}
