package controller.employee;

import controller.basis.Controller;
import dao.EmployeeDAO;
import enums.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Client;
import model.Employee;
import service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @FXML
    private Label errorLabel;

    Employee currentEmployee;

    EmployeeService employeeService = new EmployeeService();

    @FXML
    public void initialize() {
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
        String currentDate = LocalDate.now().toString();
        ArrayList<String> errors =
            employeeService.saveOrUpdate(
                currentEmployee,
                employeeUsername.getText(),
                employeePassword.getText(),
                employeeEmail.getText(),
                employeePhone.getText(),
                employeeCpf.getText(),
                employeeRole.getValue().toString(), // TODO: fix this!!! // I won't either :b
                employeeSalary.getText(),
                LocalDate.now().toString()   // TODO: fix this!!! // I won't :b
            );

        if(errors.isEmpty()) {
            this.closeWindow(event);
        } else {
            errorLabel.setText(String.join("\n", errors));
            errorLabel.setVisible(true);
        }
    }

    public void setEmployee(Employee employee) {
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
}