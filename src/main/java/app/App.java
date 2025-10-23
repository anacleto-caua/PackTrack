package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.ViewManager;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // ✅ FIX 1: Add the leading "/" to make the path absolute
        FXMLLoader fXMLloader = new FXMLLoader(App.class.getResource("/views/login.fxml"));
        Scene scene = new Scene(fXMLloader.load());

        ViewManager manager = new ViewManager();
        manager.setMainScene(scene);

        // ✅ FIX 2: You must show the stage!
        stage.setTitle("PackTrack");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); // This line actually opens the window
    }

    public static void main(String[] args) {
        launch(args);
    }
}