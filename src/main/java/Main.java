import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader loads the FXML file (the View) and instantiates the Controller
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/cadastro_clientes.fxml"));

        // Load the Scene using the loaded FXML content
        Scene scene = new Scene(fxmlLoader.load());

        // Set the stage properties
        stage.setTitle("PackTrack - Login");
        stage.setScene(scene);
        stage.setResizable(false); // Often desirable for fixed login forms
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
