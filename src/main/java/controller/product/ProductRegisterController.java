package controller.product;

import controller.basis.Controller;
import dao.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Employee;
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

    private Product currentProduct;

    @FXML
    public void onCancel(ActionEvent event) {
        this.closeWindow(event);
    }

    @FXML
    public void onSubmit(ActionEvent event){

        if (this.currentProduct == null) {
            this.currentProduct = new Product();
        }

        BigDecimal price = new BigDecimal(productPrice.getText());
        this.currentProduct.setName(productName.getText());
        this.currentProduct.setDescription(productDescription.getText());
        this.currentProduct.setValue(this.priceToBigDecimal(productPrice.getText()));

        if (this.currentProduct.getId() == null) {
            productDAO.save(this.currentProduct);
        } else {
            productDAO.update(this.currentProduct);
        }

        this.closeWindow(event);
    }

    public void setProduct(Product product) {
        this.currentProduct = product;

        if (product != null) {
            this.productName.setText(product.getName());
            this.productDescription.setText(product.getDescription());
            this.productPrice.setText(product.getValue().toString());
        }
    }

    private BigDecimal priceToBigDecimal(String priceText) {
        if (priceText == null || priceText.isEmpty()) {
            return new BigDecimal(0);
        }
        String price = priceText.replace(",", ".");
        return new BigDecimal(price);
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
