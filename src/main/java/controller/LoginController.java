package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    
    @FXML
    protected void handleLoginButtonAction() {
        if (username.getText().equals("adm") || password.getText().equals("123")) {
            System.out.println("Successful login!");
        } else {
            System.out.println("Login failed");
        }
    }
}
