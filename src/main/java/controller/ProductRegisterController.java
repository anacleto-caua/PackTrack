package controller;

import interfaces.ProductDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ProductService;

public class ProductRegisterController {

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
    public void onSubmit(ActionEvent event){
        ProductService productService = new ProductService();

        ProductDTO productDTO = new ProductDTO(
                productName.getText(),
                productDescription.getText(),
                productPrice.getText()
        );

        productService.save(productDTO);

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public TextField getProductName() {
        return productName;
    }

    public TextField getProductDescription() {
        return productDescription;
    }

    public TextField getProductPrice() {
        return productPrice;
    }

    public void setProductName(TextField productName) {
        this.productName = productName;
    }

    public void setProductDescription(TextField productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(TextField productPrice) {
        this.productPrice = productPrice;
    }
}
