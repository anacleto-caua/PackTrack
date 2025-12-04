package controller.product;

import controller.basis.Controller;
import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import model.Product;
import util.table.TableFactory;

import java.util.List;

public class ProductListController extends Controller {

    @FXML
    private TableView<Product> productTable;

    private ProductDAO productDAO = new ProductDAO();

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

    private void refreshTableData() {
        List<Product> dbList = productDAO.findAll();
        ObservableList<Product> observableList = FXCollections.observableArrayList(dbList);

        productTable.setItems(observableList);
    }

    private void handleDeleteProduct(Product product) {
        Runnable performDelete = () -> {
            productDAO.delete(product.getId());
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
            "Confirmar Exclusão",
            "Tem certeza que deseja excluir este registro?",
            performDelete
        );
    }

    private void handleUpdateProduct(Product product) {
        try {
            System.out.println("Atualizar produto: " + product.getName());
            ViewManager.showModal("product/ProductRegister.fxml", "Atualizar " + product.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}