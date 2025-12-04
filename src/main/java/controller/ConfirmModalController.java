package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmModalController {

    @FXML private Label messageLabel;
    @FXML private Button cancelBtn;

    private Runnable onConfirmAction;

    // ViewManager calls this
    public void setContent(String message, Runnable action) {
        this.messageLabel.setText(message);
        this.onConfirmAction = action;
    }

    @FXML
    public void onConfirm() {
        if (onConfirmAction != null) {
            onConfirmAction.run(); // Execute the action passed from the other class
        }
        closeModal();
    }

    @FXML
    public void onCancel() {
        closeModal();
    }

    private void closeModal() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}