package service;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee tryLogin(String identifier, String password) {
        if (identifier == null || identifier.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        Employee employee = employeeDAO.findByUsernameOrEmail(identifier);

        if (employee != null) {
            if (employee.getPassword().equals(password)){
               return employee;
            }
        }

        return null;
    }
}
