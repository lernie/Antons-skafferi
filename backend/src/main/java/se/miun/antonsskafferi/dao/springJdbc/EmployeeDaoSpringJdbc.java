package se.miun.antonsskafferi.dao.springJdbc;

import se.miun.antonsskafferi.Models.Employee;
import se.miun.antonsskafferi.dao.EmployeeDao;

import java.util.List;

public class EmployeeDaoSpringJdbc implements EmployeeDao{
    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public boolean insert(Employee employee) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public String validate(Employee employee) {
        return null;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }
}
