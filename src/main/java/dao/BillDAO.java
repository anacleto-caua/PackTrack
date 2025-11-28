package dao;

import dao.generic.GenericDAO;
import model.Bill;

public class BillDAO extends GenericDAO<Bill> {
    public BillDAO() {
        super(Bill.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
