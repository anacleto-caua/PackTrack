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

        FXMLLoader fXMLloader = new FXMLLoader(App.class.getResource("/views/login.fxml"));
        Scene scene = new Scene(fXMLloader.load());

        ViewManager.setMainScene(scene);

        stage.setTitle("PackTrack");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}