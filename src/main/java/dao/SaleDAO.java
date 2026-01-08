package dao;

import dao.generic.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import model.Sale;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class SaleDAO extends GenericDAO<Sale> {
    public SaleDAO() {
        super(Sale.class);
    }

    public BigDecimal sumTotalValueByDateRange(Date startDate, Date endDate) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT SUM(s.totalValue) FROM Sale s WHERE s.date BETWEEN :startDate AND :endDate";
            TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);

            query.setParameter("startDate", startDate, TemporalType.TIMESTAMP);
            query.setParameter("endDate", endDate, TemporalType.TIMESTAMP);

            BigDecimal result = query.getSingleResult();
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }

    public List<Sale> findRecent(int limit) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT s FROM Sale s ORDER BY s.date DESC";
            TypedQuery<Sale> query = em.createQuery(jpql, Sale.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }
}
