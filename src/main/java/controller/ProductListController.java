package controller;

import controller.basis.Controller;
import interfaces.ProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import manager.ViewManager;
import utils.TableInitializer;

public class ProductListController extends Controller {

    @FXML
    private TableView<ProductDTO> productTable;

    @FXML
    public void initialize() {
        TableInitializer.initializeTable(
                productTable,
                ProductDTO.class,
                this::handleDeleteProduct,
                this::handleUpdateProduct
        );

        productTable.setItems(loadMockData());

        ViewManager.loadStyle("style.css");
    }

    private ObservableList<ProductDTO> loadMockData() {
        return FXCollections.observableArrayList(
                new ProductDTO("Caixa Padrão", "Caixa de papelão reforçada 20x20x10cm", "R$ 5,50"),
                new ProductDTO("Fita Adesiva", "Rolo de fita adesiva transparente (50m)", "R$ 12,90"),
                new ProductDTO("Etiquetas", "Pacote com 100 etiquetas de remessa", "R$ 8,00"),
                new ProductDTO("Envelopes A4", "Envelope de segurança acolchoado", "R$ 3,20"),
                new ProductDTO("Pallet PBR", "Pallet de madeira para transporte padrão", "R$ 80,00")
        );
    }

    private void handleDeleteProduct(ProductDTO product) {
        System.out.println("Apagar produto: " + product.name());
        ViewManager.showModal("ProductRegister.fxml", "Apagar " + product.name());
    }

    private void handleUpdateProduct(ProductDTO product) {
        System.out.println("Atualizar produto (simulando salvamento): " + product.name());
        ViewManager.showModal("ProductRegister.fxml", "Atualizar " + product.name());
    }
}
