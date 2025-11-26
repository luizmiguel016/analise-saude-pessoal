package dao;

import util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class GenericDAO<T, ID extends Serializable> {
    protected final Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entity) {
        executeInsideTransaction(em -> em.persist(entity));
    }

    public void atualizar(T entity) {
        executeInsideTransaction(em -> em.merge(entity));
    }

    public void removerPorId(ID id) {
        executeInsideTransaction(em -> {
            T entity = em.find(entityClass, id);
            if (entity != null) em.remove(entity);
        });
    }

    public T buscarPorId(ID id) {
        return executeReadOnly(em -> em.find(entityClass, id));
    }

    public List<T> listarTodos() {
        return executeReadOnly(em -> {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        });
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    protected <R> R executeReadOnly(Function<EntityManager, R> function) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }
}