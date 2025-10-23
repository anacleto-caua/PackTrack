package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import manager.ViewManager;

public class DashboardController {

    @FXML
    private VBox contentBody;

    @FXML
    public void handleDashboard() {
        System.out.println("Navegar para: Dashboard");
    }

    @FXML
    public void handleClients() {
        System.out.println("Navegar para: Gestão de Clientes");
    }

    @FXML
    public void handleSupliers() {
        System.out.println("Navegar para: Gestão de Fornecedores");

        ViewManager.swapVBox(contentBody, "SupplierRegister.fxml");
    }

    @FXML
    public void handleEmployees() {
        System.out.println("Navegar para: Gestão de Funcionarios");
    }

    @FXML
    public void handleProducts() {
        System.out.println("Navegar para: Gestão de Produtos");
    }

}
