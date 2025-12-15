package service;

import dao.EmployeeDAO;
import enums.Roles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Employee;
import validator.ValidatorMaster;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public void delete(Employee employee) {
        employeeDAO.delete(employee.getId());
    }

    public ObservableList<Employee> getEmployeesList() {
        List<Employee> dbList = employeeDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public ArrayList<String> saveOrUpdate(
            Employee employee,
            String username,
            String password,
            String email,
            String phone,
            String cpf,
            String role,
            String salary,
            String hireDate) {

        ArrayList<String> errors = new ArrayList<>();

        errors.addAll(ValidatorMaster.notEmpty(username));
        errors.addAll(ValidatorMaster.notEmpty(password));
        errors.addAll(ValidatorMaster.isEmail(email));
        errors.addAll(ValidatorMaster.isPhoneNumber(phone));
        errors.addAll(ValidatorMaster.isCPF(cpf));
        errors.addAll(ValidatorMaster.isValidEnum(role, Roles.class));
        errors.addAll(ValidatorMaster.isDecimal(salary));
        errors.addAll(ValidatorMaster.isValidDate(hireDate));

        if(!errors.isEmpty()) {
            return errors;
        }

        if (employee == null) {
            employee = new Employee();
        }

        try {
            employee.setUsername(username);
            employee.setPassword(password); // Ideally we should encrypt this here (and add salt)!
            employee.setEmail(email);
            employee.setPhone(phone);
            employee.setCpf(cpf);

            employee.setRole(Roles.valueOf(role));
            employee.setSalary(new BigDecimal(salary));
            employee.setHireDate(java.sql.Date.valueOf(hireDate));

        } catch (IllegalArgumentException | NullPointerException e) {
            errors.add("Error processing data types: " + e.getMessage());
            return errors;
        }

        if (employee.getId() == null) {
            this.save(employee);
        } else {
            this.update(employee);
        }

        return errors;
    }
}
