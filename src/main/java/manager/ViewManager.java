package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewManager {

    private Scene mainScene;

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    // This is the magic method for switching pages
    public void loadView(String fxmlFile) {
        try {
            // Construct the full path to the FXML file
            String fxmlPath = "/views/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            mainScene.setRoot(root); // Swap the content of the scene
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }
}