package controller.supplier;

import controller.basis.Controller;
import dao.SupplierDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import manager.ViewManager;
import model.Supplier;
import util.table.TableFactory;

import java.util.List;

public class SupplierListController extends Controller {

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private VBox rootPane;

    private SupplierDAO supplierDAO = new SupplierDAO();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Supplier>of("ID", s -> String.valueOf(s.getId())),
                TableFactory.Column.<Supplier>of("Name", Supplier::getName),
                TableFactory.Column.<Supplier>of("Contact", Supplier::getContact),
                TableFactory.Column.<Supplier>of("Email", Supplier::getEmail)
        );

        TableFactory<Supplier> factory = new TableFactory<>(columns);
        factory.initializeTable(supplierTable, this::handleDeleteSupplier, this::handleUpdateSupplier);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    @FXML
    public void openCreationModal() {
        ViewManager.showModal("supplier/SupplierRegister.fxml", "Cadastrar Funcionario", rootPane);
        refreshTableData();
    }

    private void refreshTableData() {
        List<Supplier> dbList = supplierDAO.findAll();
        ObservableList<Supplier> observableList = FXCollections.observableArrayList(dbList);

        supplierTable.setItems(observableList);
    }

    private void handleDeleteSupplier(Supplier supplier) {
        Runnable performDelete = () -> {
            supplierDAO.delete(supplier.getId());
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
            "Confirmar Exclusão",
            "Tem certeza que deseja excluir este registro?",
            performDelete
        );
    }

    private void handleUpdateSupplier(Supplier supplier) {
        try {
            System.out.println("Atualizar fornecedor: " + supplier.getName());
            ViewManager.showModal("supplier/SupplierRegister.fxml", "Atualizar " + supplier.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}