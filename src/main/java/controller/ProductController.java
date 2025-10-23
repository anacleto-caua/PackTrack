package controller;

import interfaces.ProductDTO;
import interfaces.SupplierDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ProductService;

import java.math.BigDecimal;

public class ProductController {

    @FXML
    private TextField productName;

    @FXML
    private TextField productDescription;

    // TODO: CHANGE TEXTFIELD TO A MORE SUITABLE FIELD
    @FXML
    private TextField productPrice;

    @FXML
    public void onCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSubmit(){
        ProductService productService = new ProductService();

        ProductDTO productDTO = new ProductDTO(
                productName.getText(),
                productDescription.getText(),
                productPrice.getText()
        );

        productService.save(productDTO);
    }
}
