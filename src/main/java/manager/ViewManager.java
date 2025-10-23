package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewManager {

    private static final ViewManager instance = new ViewManager();

    private static Scene mainScene;

    private ViewManager() { // So no one tries to create a new instance
    }

    public static ViewManager getInstance() {
        return instance;
    }

    public static void loadView(String fxmlFile) {
        try {
            String fxmlPath = "/views/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            // Swap the content of the scene
            instance.mainScene.setRoot(root);

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlFile);
            e.printStackTrace();

        }
    }

    public static Scene getMainScene() {
        return instance.mainScene;
    }

    public static void setMainScene(Scene mainScene) {
        instance.mainScene = mainScene;
    }
}