package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GenericDAO<T> {

    public  static  final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoSaudePU");

    private final Class<T> classe;

    public GenericDAO(Class<T> classe) {
        this.classe = classe;
    }

    public T salvar(T entidade) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
            return entidade;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public T editar(T entidade) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
            return entidade;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public T buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        T entidade =  em.find(classe, id);
        em.close();
        return entidade;
    }

    public List<T> buscarTodos() {
        EntityManager em = emf.createEntityManager();
        List<T> lista = em.createQuery("SELECT t FROM " + classe.getSimpleName() + " t", classe)
                .getResultList();
        em.close();
        return lista;
    }

    public void remover(T entidade) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (entidade != null) {
                T managed = em.merge(entidade);
                em.remove(managed);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void removerPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            T entidade = em.find(classe, id);
            if (entidade != null) {
                em.remove(entidade);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<T> listar(String nomeCampo, Object valor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM " + classe.getSimpleName() + " t WHERE t. " + nomeCampo + " = :valor", classe)
                    .setParameter("valor", valor)
                    .getResultList();
        } catch (NullPointerException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
        finally {
            em.close();
        }

    }
}