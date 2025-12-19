package service;

import dao.ProductDAO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import validator.ValidatorMaster;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

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

    public void saveOrUpdate(Product product) {
        validate(product);

        if (product.getId() == null) {
            productDAO.save(product);
        } else {
            productDAO.update(product);
        }
    }

    private void validate(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            throw new ValidationException(errors);
        }
    }
}