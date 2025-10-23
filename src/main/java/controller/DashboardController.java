package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DashboardController {

    @FXML
    private VBox contentArea;

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
