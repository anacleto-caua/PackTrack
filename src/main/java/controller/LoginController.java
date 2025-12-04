package controller;

import controller.basis.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.ViewManager;
import service.EmployeeService;

import javax.swing.text.View;

public class LoginController extends Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    EmployeeService employeeService = new EmployeeService();

    @FXML
    protected void handleLoginButtonAction() {
        var loggedEmployee = employeeService.tryLogin(username.getText(), password.getText());
        if (loggedEmployee != null) {
            System.out.println("Successful login! From: " + loggedEmployee.getUsername());
            ViewManager.loadView("Dashboard.fxml");
        } else {
            System.out.println("Login failed!");
        }
    }
}
