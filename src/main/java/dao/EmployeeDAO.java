package dao;

import dao.generic.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.JPAUtil;

import java.util.List;

public class EmployeeDAO extends GenericDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    public Employee findByUsernameOrEmail(String identifier) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT e FROM Employee e WHERE e.username = :ident OR e.email = :ident";

            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("ident", identifier);

            List<Employee> results = query.getResultList();

            return results.isEmpty() ? null : results.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
