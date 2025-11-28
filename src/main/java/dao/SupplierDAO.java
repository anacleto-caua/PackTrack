package dao;

import dao.generic.GenericDAO;
import model.Supplier;

public class SupplierDAO extends GenericDAO<Supplier> {
    public SupplierDAO() {
        super(Supplier.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
