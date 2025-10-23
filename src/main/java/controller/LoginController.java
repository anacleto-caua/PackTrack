package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.ViewManager;

import javax.swing.text.View;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    protected void handleLoginButtonAction() {
        if (username.getText().equals("adm") && password.getText().equals("123")) {
            System.out.println("Successful login!");

            System.out.println("SCENE IN CONTROLLER: " + ViewManager.getMainScene());


            ViewManager.loadView("dashboard.fxml");

        } else {
            System.out.println("Login failed!");
        }
    }
}
