package controller.product;

import controller.basis.Controller;
import controller.employee.EmployeeRegisterController;
import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import manager.ViewManager;
import model.Product;
import service.ProductService;
import util.table.TableFactory;

import java.util.List;

public class ProductListController extends Controller {

    @FXML
    private TableView<Product> productTable;

    @FXML
    private VBox rootPane;

    private ProductService productService = new ProductService();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Product>of("ID", p -> String.valueOf(p.getId())),
                TableFactory.Column.<Product>of("Name", Product::getName),
                TableFactory.Column.<Product>of("Description", Product::getDescription),
                TableFactory.Column.<Product>of("Value", p -> p.getValue() != null ? String.format("R$ %.2f", p.getValue()) : "R$ 0.00")
        );

        TableFactory<Product> factory = new TableFactory<>(columns);
        factory.initializeTable(productTable, this::handleDeleteProduct, this::handleUpdateProduct);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    @FXML
    public void openCreationModal() {
        ViewManager.showModal("product/ProductRegister.fxml", "Cadastrar Produto", rootPane);
        refreshTableData();
    }

    private void refreshTableData() {
        ObservableList<Product> observableList = productService.getProductsList();

        productTable.setItems(observableList);
    }

    private void handleDeleteProduct(Product product) {
        Runnable performDelete = () -> {
            productService.delete(product);
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
            "Confirmar Exclusão",
            "Tem certeza que deseja excluir este registro?",
            performDelete
        );
    }

    private void handleUpdateProduct(Product product) {
        ViewManager.showModal("product/ProductRegister.fxml", "Atualizar Produto", rootPane,
                (ProductRegisterController controller) -> {
                    controller.setProduct(product);
                });
        refreshTableData();
    }
}