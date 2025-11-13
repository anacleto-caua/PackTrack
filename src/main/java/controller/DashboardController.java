package controller;

import controller.basis.Controller;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import manager.ViewManager;

public class DashboardController extends Controller {

    @FXML
    private VBox contentBody;

    @FXML
    public void handleDashboard() {
        System.out.println("Navegar para: Dashboard");
    }

    @FXML
    public void handleClients() {
        System.out.println("Navegar para: Gestão de Clientes");

        ViewManager.swapVBox(contentBody, "ListClient.fxml");
    }

    @FXML
    public void handleSupliers() {
        System.out.println("Navegar para: Gestão de Fornecedores");

        ViewManager.swapVBox(contentBody, "ListSupplier.fxml");
    }

    @FXML
    public void handleEmployees() {
        System.out.println("Navegar para: Gestão de Funcionarios");

        ViewManager.swapVBox(contentBody, "ListEmployee.fxml");
    }

    @FXML
    public void handleProducts() {
        System.out.println("Navegar para: Gestão de Produtos");

        ViewManager.swapVBox(contentBody, "ListProduct.fxml");
    }

}
