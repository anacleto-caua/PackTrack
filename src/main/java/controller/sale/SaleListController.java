package controller.sale;

import controller.basis.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import manager.ViewManager;
import model.Sale;
import service.SaleService;
import util.table.TableFactory;

import java.util.List;

public class SaleListController extends Controller {

    @FXML
    private TableView<Sale> saleTable;

    @FXML
    private VBox rootPane;

    private SaleService saleService = new SaleService();

    @FXML
    public void initialize() {

        var columns = List.of(
                TableFactory.Column.<Sale>of("ID", s -> String.valueOf(s.getId())),
                TableFactory.Column.<Sale>of("Cliente", s -> s.getClient().getName()),
//                TableFactory.Column.<Sale>of("Cliente", s -> s.getClient().getName()), //ITEMS
                TableFactory.Column.<Sale>of("Valor total", s -> String.valueOf(s.getTotalValue())),
                TableFactory.Column.<Sale>of("Data", s -> s.getDate().toString())
        );

        TableFactory<Sale> factory = new TableFactory<>(columns);
        factory.initializeTable(saleTable, this::handleDeleteSale, this::handleUpdateSale);

        refreshTableData();

        ViewManager.loadStyle("style.css");
    }

    @FXML
    public void openCreationModal() {
        ViewManager.showModal("sales/SalesRegister.fxml", "Cadastrar Venda", rootPane);
        refreshTableData();
    }

    private void refreshTableData() {
        ObservableList<Sale> observableList = saleService.getSalesList();

        saleTable.setItems(observableList);
    }

    private void handleDeleteSale(Sale sale) {
        Runnable performDelete = () -> {
            saleService.delete(sale);
            this.refreshTableData();
        };

        ViewManager.showConfirmDialog(
                "Confirmar Exclusão",
                "Tem certeza que deseja excluir este registro?",
                performDelete
        );
    }

    private void handleUpdateSale(Sale sale) {
        ViewManager.showModal("sale/SaleRegister.fxml", "Atualizar Venda", rootPane,
                (SaleRegisterController controller) -> {
                    controller.setSale(sale);
                });
        refreshTableData();
    }
}