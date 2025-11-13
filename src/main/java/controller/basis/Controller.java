package controller.basis;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class Controller {
    protected void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
