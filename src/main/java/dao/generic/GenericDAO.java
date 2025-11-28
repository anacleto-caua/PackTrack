package dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;
import java.util.List;

public abstract class GenericDAO<T> {

    private final Class<T> entityClass;

    // We need to know what class 'T' is at runtime
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity); // Insert
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity); // Update
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // We must find the object first to delete it
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public T findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // "FROM T" doesn't work in JPQL, we use the class name
            String jpql = "SELECT t FROM " + entityClass.getSimpleName() + " t";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}