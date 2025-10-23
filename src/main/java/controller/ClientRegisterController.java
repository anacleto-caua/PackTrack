package controller;

import interfaces.ClientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ClientService;

public class ClientRegisterController {
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPhone;
    @FXML
    private TextField clientAddress;

    @FXML
    private void onCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        ClientService clientService = new ClientService();

        ClientDTO clientDTO = new ClientDTO(
                clientName.getText(),
                clientEmail.getText(),
                clientPhone.getText(),
                clientAddress.getText()
        );

        clientService.save(clientDTO);
    }

    public TextField getClientName() {
        return clientName;
    }

    public TextField getClientEmail() {
        return clientEmail;
    }

    public TextField getClientPhone() {
        return clientPhone;
    }

    public TextField getClientAddress() {
        return clientAddress;
    }

    public void setClientName(TextField clientName) {
        this.clientName = clientName;
    }

    public void setClientPhone(TextField clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setClientEmail(TextField clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientAddress(TextField clientAddress) {
        this.clientAddress = clientAddress;
    }
}
