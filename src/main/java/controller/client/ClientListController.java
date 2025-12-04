package controller.client;

import controller.basis.Controller;
import dao.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import manager.ViewManager;
import model.Client;
import util.table.TableFactory;

import java.util.List;

public class ClientListController extends Controller {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private VBox rootPane;

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

    @FXML
    public void openCreationModal() {
        ViewManager.showModal("client/ClientRegister.fxml", "Cadastrar Cliente", rootPane);
        refreshTableData();
    }

    private void refreshTableData() {
        List<Client> dbList = clientDAO.findAll();
        ObservableList<Client> observableList = FXCollections.observableArrayList(dbList);

        clientTable.setItems(observableList);
    }

    private void handleDeleteClient(Client client) {
        Runnable performDelete = () -> {
            clientDAO.delete(client.getId());
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
            "Confirmar Exclusão",
            "Tem certeza que deseja excluir este registro?",
            performDelete
        );
    }

    private void handleUpdateClient(Client client) {
        ViewManager.showModal("client/ClientRegister.fxml", "Atualizar Cliente", rootPane,
                (ClientRegisterController controller) -> {
                    controller.setClient(client);
                });
        refreshTableData();
    }
}
