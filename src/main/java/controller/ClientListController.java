package controller;

import interfaces.ClientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import utils.TableInitializer;

public class ClientListController {

    @FXML
    private TableView<ClientDTO> clientTable;

    @FXML
    public void initialize() {
        TableInitializer.initializeTable(
                clientTable,
                ClientDTO.class,
                this::handleDeleteClient,
                this::handleUpdateClient
        );

        clientTable.setItems(loadMockData());

        ViewManager.loadStyle("style.css");
    }

    private ObservableList<ClientDTO> loadMockData() {
        return FXCollections.observableArrayList(
                new ClientDTO("Ana Souza", "ana.souza@email.com", "(11) 98765-4321", "Rua A, 100"),
                new ClientDTO("Bruno Lima", "bruno.lima@email.com", "(21) 99876-5432", "Av. B, 250"),
                new ClientDTO("Carlos Mello", "carlos.mello@email.com", "(31) 97654-3210", "Travessa C, 50"),
                new ClientDTO("Diana Cruz", "diana.cruz@email.com", "(41) 96543-2109", "Alameda D, 300")
        );
    }

    private void handleDeleteClient(ClientDTO client) {
        System.out.println("Apagar cliente: " + client.name());
    }

    private void handleUpdateClient(ClientDTO client) {
        System.out.println("Atualizar cliente: " + client.name());
    }
}
