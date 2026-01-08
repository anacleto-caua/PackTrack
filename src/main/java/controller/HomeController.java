package controller;

import controller.basis.Controller;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import manager.ViewManager;

public class HomeController extends Controller {

    @FXML
    private VBox contentBody;

    @FXML
    public void initialize() {
        handleDashboard();
    }

    @FXML
    public void handleDashboard() {
        System.out.println("Navegar para: Dashboard");

        ViewManager.swapVBox(contentBody, "dashboard/Dashboard.fxml");
    }

    @FXML
    public void handleClients() {
        System.out.println("Navegar para: Gestão de Clientes");

        ViewManager.swapVBox(contentBody, "client/ListClient.fxml");
    }

    @FXML
    public void handleSuppliers() {
        System.out.println("Navegar para: Gestão de Fornecedores");

        ViewManager.swapVBox(contentBody, "supplier/ListSupplier.fxml");
    }

    public void handleSales() {
        System.out.println("Navegar para: Gestão de Vendas");

        ViewManager.swapVBox(contentBody, "sales/ListSales.fxml");
    }

    @FXML
    public void handleEmployees() {
        System.out.println("Navegar para: Gestão de Funcionarios");

        ViewManager.swapVBox(contentBody, "employee/ListEmployee.fxml");
    }

    @FXML
    public void handleProducts() {
        System.out.println("Navegar para: Gestão de Produtos");

        ViewManager.swapVBox(contentBody, "product/ListProduct.fxml");
    }
}
