package controller.client;

import controller.basis.Controller;
import dao.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import model.Client;
import util.table.TableFactory;

import java.util.List;

public class ClientListController extends Controller {

    @FXML
    private TableView<Client> clientTable;

    private ClientDAO clientDAO = new  ClientDAO();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Client>of("ID", c -> String.valueOf(c.getId())),
                TableFactory.Column.<Client>of("Name", Client::getName),
                TableFactory.Column.<Client>of("Phone", Client::getPhone),
                TableFactory.Column.<Client>of("Email", Client::getEmail)
        );

        TableFactory<Client> factory = new TableFactory<Client>(columns);
        factory.initializeTable(clientTable, this::handleDeleteClient, this::handleUpdateClient);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    private void refreshTableData() {
        List<Client> dbList = clientDAO.findAll();
        ObservableList<Client> observableList = FXCollections.observableArrayList(dbList);

        clientTable.setItems(observableList);
    }

    private void handleDeleteClient(Client client) {
        System.out.println("Apagar cliente: " + client.getName());
        ViewManager.showModal("ClientRegister.fxml", "Apagar " + client.getName());
    }

    private void handleUpdateClient(Client client) {
        System.out.println("Atualizar cliente: " + client.getName());
        ViewManager.showModal("ClientRegister.fxml", "Atualizar " + client.getName());

    }
}
