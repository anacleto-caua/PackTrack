package controller.supplier;

import controller.basis.Controller;
import interfaces.SupplierDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import util.TableInitializer;

public class SupplierListController extends Controller {

    @FXML
    private TableView<SupplierDTO> supplierTable;

    @FXML
    public void initialize() {
        TableInitializer.initializeTable(
                supplierTable,
                SupplierDTO.class,
                this::handleDeleteSupplier,
                this::handleUpdateSupplier
        );

        supplierTable.setItems(loadMockData());

        ViewManager.loadStyle("style.css");
    }

    private ObservableList<SupplierDTO> loadMockData() {
        return FXCollections.observableArrayList(
                new SupplierDTO("Fornecedor A", "contato@fornecedora.com", "(11) 2222-3333"),
                new SupplierDTO("Distribuidora B", "vendas@distribuidorab.com", "(21) 4444-5555"),
                new SupplierDTO("Importados C", "sac@importadosc.com", "(31) 6666-7777")
        );
    }

    private void handleDeleteSupplier(SupplierDTO supplier) {
        System.out.println("Apagar fornecedor: " + supplier.name());
        ViewManager.showModal("SupplierRegister.fxml", "Apagar " + supplier.name());
    }

    private void handleUpdateSupplier(SupplierDTO supplier) {
        System.out.println("Atualizar fornecedor: " + supplier.name());
        ViewManager.showModal("SupplierRegister.fxml", "Atualizar " + supplier.name());
    }
}