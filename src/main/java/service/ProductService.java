package service;

import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import validator.ValidatorMaster;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public void save(Product product) {
        productDAO.save(product);
    }

    public void update(Product product) {
        productDAO.update(product);
    }

    public void delete(Product product) {
        productDAO.delete(product.getId());
    }

    public ObservableList<Product> getProductsList() {
        List<Product> dbList = productDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public ArrayList<String> saveOrUpdate(Product product, String name, String description, String valueStr) {
        ArrayList<String> errors = new ArrayList<>();

        errors.addAll(ValidatorMaster.notEmpty(name));
        errors.addAll(ValidatorMaster.notEmpty(description));
        errors.addAll(ValidatorMaster.isBigDecimal(valueStr));

        if (!errors.isEmpty()) {
            return errors;
        }

        if (product == null) {
            product = new Product();
        }

        product.setName(name);
        product.setDescription(description);
        product.setValue(new BigDecimal(valueStr));

        if (product.getId() == null) {
            this.save(product);
        } else {
            this.update(product);
        }

        return errors;
    }
}