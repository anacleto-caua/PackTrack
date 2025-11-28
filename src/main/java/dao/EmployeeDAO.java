package dao;

import dao.generic.GenericDAO;
import model.Employee;

public class EmployeeDAO extends GenericDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
