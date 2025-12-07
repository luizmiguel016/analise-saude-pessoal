package dao;

import domain.RegistroSaude;
import domain.Usuario;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class RegistroSaudeDAO extends GenericDAO<RegistroSaude> {

    public RegistroSaudeDAO() {
        super(RegistroSaude.class);
    }

    public RegistroSaude buscarPorDataEUsuario(LocalDate data, Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM RegistroSaude r WHERE r.data = :data AND r.usuario = :usuario",
                    RegistroSaude.class)
                    .setParameter("data", data)
                    .setParameter("usuario", usuario)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<RegistroSaude> buscarPorPeriodo(Usuario usuario, LocalDate inicio, LocalDate fim) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM RegistroSaude r WHERE r.usuario = :usuario AND r.data BETWEEN :inicio AND :fim ORDER BY r.data DESC",
                            RegistroSaude.class)
                    .setParameter("usuario", usuario)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
