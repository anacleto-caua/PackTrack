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
    protected void handleMock() {
        System.out.println("mock!!!!!");
    }

    @FXML
    protected void handleLoginButtonAction() {
        System.out.println("mock login button!");
    }
}
