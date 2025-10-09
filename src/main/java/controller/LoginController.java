package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label mensagemAcao;

    @FXML
    protected void handleLoginButtonAction() {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        if ("user".equals(usuario) && "pass".equals(senha)) {
            mensagemAcao.setText("Login Efetuado com Sucesso! 🎉");
            mensagemAcao.setStyle("-fx-text-fill: green;");
        } else {
            mensagemAcao.setText("Credenciais inválidas. Tente novamente.");
            mensagemAcao.setStyle("-fx-text-fill: red;");
        }
        campoSenha.clear();
    }
}
