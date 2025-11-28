package dao;

import dao.generic.GenericDAO;
import model.Product;

public class ProductDAO extends GenericDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
