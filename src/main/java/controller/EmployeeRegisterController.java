package controller;

import interfaces.EmployeeDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.EmployeeService;

public class EmployeeRegisterController {
    @FXML
    private TextField employeeName;
    @FXML
    private TextField employeeEmail;
    @FXML
    private TextField employeePassword;
    @FXML
    private TextField employeeRole;
    @FXML
    private TextField employeeSalary;
    @FXML
    private TextField employeeCommission;

    @FXML
    public void onCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSubmit() {
        EmployeeService employeeService = new EmployeeService();

        EmployeeDTO employee = new EmployeeDTO(
                employeeName.getText(),
                employeeEmail.getText(),
                employeePassword.getText(),
                employeeRole.getText(),
                employeeSalary.getText(),
                employeeCommission.getText()
        );

        employeeService.save(employee);
    }

    public TextField getEmployeeName() {
        return employeeName;
    }

    public TextField getEmployeeEmail() {
        return employeeEmail;
    }

    public TextField getEmployeePassword() {
        return employeePassword;
    }

    public TextField getEmployeeRole() {
        return employeeRole;
    }

    public TextField getEmployeeSalary() {
        return employeeSalary;
    }

    public TextField getEmployeeCommission() {
        return employeeCommission;
    }

    public void setEmployeeName(TextField employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeEmail(TextField employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeePassword(TextField employeePassword) {
        this.employeePassword = employeePassword;
    }

    public void setEmployeeRole(TextField employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void setEmployeeSalary(TextField employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void setEmployeeCommission(TextField employeeCommission) {
        this.employeeCommission = employeeCommission;
    }
}
