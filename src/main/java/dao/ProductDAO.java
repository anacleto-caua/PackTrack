package dao;

import dao.generic.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Product;
import util.JPAUtil;

import java.util.List;

public class ProductDAO extends GenericDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }

    public List<Product> findLowStock(int threshold) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.quantity <= :threshold ORDER BY p.quantity ASC";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("threshold", threshold);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

}
