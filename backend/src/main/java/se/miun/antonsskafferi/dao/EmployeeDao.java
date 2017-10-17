package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    boolean insert(Employee emp);
    String validate(Employee emp) throws Exception;
    boolean update(Employee emp);
    boolean delete(int id);
}
