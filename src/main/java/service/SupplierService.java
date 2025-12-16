package service;

import dao.SupplierDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import validator.ValidatorMaster;

import java.util.ArrayList;
import java.util.List;

public class SupplierService {

    private SupplierDAO supplierDAO = new SupplierDAO();

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

    public ArrayList<String> saveOrUpdate(Supplier supplier, String name, String contact, String email) {
        ArrayList<String> errors = new ArrayList<>();

        errors.addAll(ValidatorMaster.notEmpty(name));
        errors.addAll(ValidatorMaster.notEmpty(contact));
        errors.addAll(ValidatorMaster.isPhoneNumber(contact));
        errors.addAll(ValidatorMaster.isEmail(email));

        if (!errors.isEmpty()) {
            return errors;
        }

        if (supplier == null) {
            supplier = new Supplier();
        }

        supplier.setName(name);
        supplier.setContact(contact);
        supplier.setEmail(email);

        if (supplier.getId() == null) {
            this.save(supplier);
        } else {
            this.update(supplier);
        }

        return errors;
    }
}