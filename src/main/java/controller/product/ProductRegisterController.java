package controller.product;

import controller.basis.Controller;
import dao.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Product;

import java.math.BigDecimal;

public class ProductRegisterController extends Controller {

    @FXML
    private TextField productName;

    @FXML
    private TextField productDescription;

    // TODO: CHANGE TEXTFIELD TO A MORE SUITABLE FIELD
    @FXML
    private TextField productPrice;

    private ProductDAO productDAO = new ProductDAO();

    @FXML
    public void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event){

        String priceText = productPrice.getText().replace(",", ".");
        BigDecimal finalPrice = new BigDecimal(priceText);

        Product product = new Product(
            null,
            productName.getText(),
            productDescription.getText(),
            finalPrice
        );

        productDAO.save(product);

        this.closeWindow(event);
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
