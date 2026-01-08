package service;

import dao.SaleDAO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Sale;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SaleService {
    private SaleDAO saleDAO = new SaleDAO();

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public void save(Sale sale) {
        saleDAO.save(sale);
    }

    public void update(Sale sale) {
        saleDAO.update(sale);
    }

    public void delete(Sale sale) {
        saleDAO.delete(sale.getId());
    }

    public ObservableList<Sale> getSalesList() {
        List<Sale> dbList = saleDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public void saveOrUpdate(Sale sale) {
        validate(sale);

        if (sale.getId() == null) {
            saleDAO.save(sale);
        } else {
            saleDAO.update(sale);
        }
    }

    private void validate(Sale sale) {
        Set<ConstraintViolation<Sale>> violations = validator.validate(sale);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            throw new ValidationException(errors);
        }
    }
}
