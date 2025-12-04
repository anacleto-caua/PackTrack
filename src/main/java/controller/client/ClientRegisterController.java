package controller.client;

import controller.basis.Controller;
import dao.ClientDAO;
import interfaces.ClientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;
import service.ClientService;

public class ClientRegisterController extends Controller {
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPhone;
    @FXML
    private TextField clientAddress;

    private ClientDAO clientDAO = new  ClientDAO();

    @FXML
    private void onCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        Client client = new Client();

        client.setName(clientName.getText());
        client.setEmail(clientEmail.getText());
        client.setPhone(clientPhone.getText());

        clientDAO.save(client);

        this.closeWindow(event);
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
