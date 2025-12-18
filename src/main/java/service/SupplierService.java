package service;

import dao.SupplierDAO;
import jakarta.validation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SupplierService {

    private SupplierDAO supplierDAO = new SupplierDAO();

    // TODO: Make a propper factory
    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public void save(Supplier supplier) {
        supplierDAO.save(supplier);
    }

    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
    }

    public void delete(Supplier supplier) {
        supplierDAO.delete(supplier.getId());
    }

    public ObservableList<Supplier> getSuppliersList() {
        List<Supplier> dbList = supplierDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public void saveOrUpdate(Supplier supplier) {
        validate(supplier);

        if (supplier.getId() == null) {
            supplierDAO.save(supplier);
        } else {
            supplierDAO.update(supplier);
        }
    }

    private void validate(Supplier supplier) {
        Set<ConstraintViolation<Supplier>> violations = validator.validate(supplier);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));

           throw new ValidationException(errorMessage);
        }
    }
}