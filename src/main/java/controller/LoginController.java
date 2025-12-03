package controller;

import controller.basis.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.ViewManager;

import javax.swing.text.View;

public class LoginController extends Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    protected void handleLoginButtonAction() {
        if (username.getText().equals("adm") && password.getText().equals("123")) {
            System.out.println("Successful login!");

            System.out.println("SCENE IN CONTROLLER: " + ViewManager.getMainScene());


            ViewManager.loadView("Dashboard.fxml");

        } else {
            System.out.println("Login failed!");
        }
    }
}
