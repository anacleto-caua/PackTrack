package controller.product;

import controller.basis.Controller;
import dao.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductRegisterController extends Controller {

    @FXML
    private TextField productName;
    @FXML
    private TextField productDescription;
    @FXML
    private TextField productPrice; // TODO: CHANGE FROM TEXTFIELD TO A MORE SUITABLE FIELD
    @FXML
    private Label errorLabel;

    private Product currentProduct;

    private ProductService productService = new ProductService();

    @FXML
    public void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event){

        if (this.currentProduct == null) {
            this.currentProduct = new Product();
        }
        ArrayList<String> errors =
            productService.saveOrUpdate(
                currentProduct,
                productName.getText(),
                productDescription.getText(),
                productPrice.getText()
            );

        if(errors.isEmpty()) {
            this.closeWindow(event);
        } else {
            errorLabel.setText(String.join("\n", errors));
            errorLabel.setVisible(true);
        }
    }

    public void setProduct(Product product) {
        this.currentProduct = product;

        if (product != null) {
            this.productName.setText(product.getName());
            this.productDescription.setText(product.getDescription());
            this.productPrice.setText(product.getValue().toString());
        }
    }
}
