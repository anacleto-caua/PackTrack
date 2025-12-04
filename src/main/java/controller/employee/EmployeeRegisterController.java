package controller.employee;

import controller.basis.Controller;
import dao.EmployeeDAO;
import enums.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Client;
import model.Employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeRegisterController extends Controller {

    @FXML
    private TextField employeeUsername; // Matches User.username
    @FXML
    private TextField employeeEmail;    // Matches User.email
    @FXML
    private PasswordField employeePassword; // Matches User.password
    @FXML
    private TextField employeePhone;    // Matches User.phone
    @FXML
    private TextField employeeCpf;      // Matches User.cpf
    @FXML
    private ComboBox<Roles> employeeRole; // Matches User.role
    @FXML
    private TextField employeeSalary;   // Matches Employee.salary

    EmployeeDAO employeeDAO = new EmployeeDAO();

    Employee currentEmployee;

    @FXML
    public void initialize() {
        // Populate the ComboBox with the Roles Enum values on window load
        if (employeeRole != null) {
            employeeRole.getItems().setAll(Roles.values());
        }
    }

    @FXML
    public void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        if (this.currentEmployee == null) {
            this.currentEmployee = new Employee();
        }

        BigDecimal salary = new BigDecimal(employeeSalary.getText().replace(",", "."));

        this.currentEmployee.setUsername(employeeUsername.getText());
        this.currentEmployee.setEmail(employeeEmail.getText());
        this.currentEmployee.setPassword(employeePassword.getText());
        this.currentEmployee.setPhone(employeePhone.getText());
        this.currentEmployee.setCpf(employeeCpf.getText());
        this.currentEmployee.setRole(employeeRole.getValue());
        this.currentEmployee.setSalary(salary);

        if (this.currentEmployee.getId() == null) {
            this.currentEmployee.setHireDate(new Date()); // TODO: fix this!!!
            employeeDAO.save(this.currentEmployee);
        } else {
            employeeDAO.update(this.currentEmployee);
        }

        this.closeWindow(event);
    }

    public void setClient(Employee employee) {
        this.currentEmployee = employee;

        if (employee != null) {
            this.employeeUsername.setText(employee.getUsername());
            this.employeeEmail.setText(employee.getEmail());
            this.employeePassword.setText(employee.getPassword());
            this.employeePhone.setText(employee.getPhone());
            this.employeeCpf.setText(employee.getCpf());
            this.employeeRole.setValue(employee.getRole());
            this.employeeSalary.setText(employee.getSalary().toString());
        }
    }

    public TextField getEmployeeUsername() { return employeeUsername; }
    public void setEmployeeUsername(TextField employeeUsername) { this.employeeUsername = employeeUsername; }

    public TextField getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(TextField employeeEmail) { this.employeeEmail = employeeEmail; }

    public PasswordField getEmployeePassword() { return employeePassword; }
    public void setEmployeePassword(PasswordField employeePassword) { this.employeePassword = employeePassword; }

    public TextField getEmployeePhone() { return employeePhone; }
    public void setEmployeePhone(TextField employeePhone) { this.employeePhone = employeePhone; }

    public TextField getEmployeeCpf() { return employeeCpf; }
    public void setEmployeeCpf(TextField employeeCpf) { this.employeeCpf = employeeCpf; }

    public ComboBox<Roles> getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(ComboBox<Roles> employeeRole) { this.employeeRole = employeeRole; }

    public TextField getEmployeeSalary() { return employeeSalary; }
    public void setEmployeeSalary(TextField employeeSalary) { this.employeeSalary = employeeSalary; }
}