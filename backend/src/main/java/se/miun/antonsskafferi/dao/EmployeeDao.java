package se.miun.antonsskafferi.dao;

import se.miun.antonsskafferi.Models.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    boolean insert(Employee employee);
    boolean delete(int id);
    String validate(Employee employee);
    boolean update(Employee employee);
}