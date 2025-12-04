package manager;

import controller.ConfirmModalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Objects;

public class ViewManager {

    private static String MODAL_VIEW_NAME = "ConfirmModal.fxml";

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

    public static Parent loadFXML(String fxmlFile) {
        try {
            String fxmlPath = "/views/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
            return loader.load();

        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + fxmlFile);
            e.printStackTrace();
            return null;
        }
    }

    public static void loadStyle(String cssFile) {
        try {
            String cssPath = "/styles/" + cssFile;
            ViewManager.mainScene.getStylesheets().add(ViewManager.class.getResource(cssPath).toExternalForm());

        } catch (Exception e) {
            System.err.println("Failed to load CSS file: " + cssFile);
            e.printStackTrace();
        }
    }

    public static void swapVBox(VBox contentArea, String fxmlFile) {
        Parent loadedView = ViewManager.loadFXML(fxmlFile);

        if (loadedView != null) {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(loadedView);
        }
    }

    public static void showModal(String fxmlFile, String title) {
        try {
            String fxmlPath = "/views/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));

            modalStage.initModality(Modality.APPLICATION_MODAL);

            if (mainScene != null) {
                modalStage.initOwner(mainScene.getWindow());
            }

            modalStage.showAndWait(); // Exibe e espera o fechamento
        } catch (IOException e) {
            System.err.println("Error loading modal FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }

    public static void showConfirmDialog(String title, String message, Runnable onConfirmAction) {
        try {
            String fxmlPath = "/views/" + MODAL_VIEW_NAME;
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof ConfirmModalController) {
                ((ConfirmModalController) controller).setContent(message, onConfirmAction);
            }

            Stage modalStage = new Stage();
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.APPLICATION_MODAL);

            if (mainScene != null) {
                modalStage.initOwner(mainScene.getWindow());
            }

            modalStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Error loading confirm modal!");
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