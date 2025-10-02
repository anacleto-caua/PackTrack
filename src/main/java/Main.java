import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(30, 30, 30, 30));

        Text scenetitle = new Text("Bem vindo ao PackTrack");
        scenetitle.setStyle("-fx-font: normal bold 24px 'serif'; -fx-fill: #1a56db;");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Nome de Usuário:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setPromptText("Digite seu usuário");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Senha:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Digite sua senha");
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Entrar");
        btn.setPrefWidth(100);
        grid.add(btn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();

            if ("user".equals(username) && "pass".equals(password)) {
                actiontarget.setText("Login Efetuado! 🎉");
                actiontarget.setStyle("-fx-fill: green;");
            } else {
                actiontarget.setText("Credenciais Inválidas. Tente Novamente.");
                actiontarget.setStyle("-fx-fill: red;");
            }
            pwBox.setText("");
        });

        Scene scene = new Scene(grid, 800, 320);
        primaryStage.setTitle("PackTrack - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
