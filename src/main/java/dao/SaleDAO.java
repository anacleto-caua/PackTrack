package dao;

import dao.generic.GenericDAO;
import model.Sale;

public class SaleDAO extends GenericDAO<Sale> {
    public SaleDAO() {
        super(Sale.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
