package controller.client;

import controller.basis.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import model.Client;
import service.ClientService;

import java.util.ArrayList;

@Getter
@Setter
public class ClientRegisterController extends Controller {
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPhone;
    @FXML
    private TextField clientAddress;
    @FXML
    private Label errorLabel;

    private Client currentClient;

    private ClientService clientService = new ClientService();

    @FXML
    private void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        ArrayList<String> errors =
                clientService.saveOrUpdate(
                        currentClient,
                        clientName.getText(),
                        clientEmail.getText(),
                        clientPhone.getText()
                );

        if(errors.isEmpty()) {
            this.closeWindow(event);
        } else {
            errorLabel.setText(String.join("\n", errors));
            errorLabel.setVisible(true);
        }
    }

    public void setClient(Client client) {
        this.currentClient = client;

        if (client != null) {
            this.clientName.setText(client.getName());
            this.clientEmail.setText(client.getEmail());
            this.clientPhone.setText(client.getPhone());
        }
    }
}
