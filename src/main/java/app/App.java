package app;

import controller.SupplierRegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.ViewManager;
import service.SupplierService;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        SupplierService supplierService = new SupplierService();
        // ✅ FIX 1: Add the leading "/" to make the path absolute
        FXMLLoader fXMLloader = new FXMLLoader(App.class.getResource("/views/SupplierRegister.fxml"));

        fXMLloader.setControllerFactory(controllerClass -> {
            if(controllerClass.equals(SupplierRegisterController.class)) {
                return new SupplierRegisterController(supplierService);
            } else {
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Scene scene = new Scene(fXMLloader.load());

        ViewManager.setMainScene(scene);

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