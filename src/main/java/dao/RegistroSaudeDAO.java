package dao;

import domain.RegistroSaude;
import domain.Usuario;

import javax.persistence.NoResultException;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroSaudeDAO extends GenericDAO<RegistroSaude, Long> {

    public RegistroSaudeDAO() {
        super(RegistroSaude.class);
    }

    public RegistroSaude buscarPorDataEUsuario(LocalDate data, Usuario usuario) {
        return executeReadOnly(em -> {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.data = :data AND r.usuario = :usuario";
            try {
                return em.createQuery(jpql, RegistroSaude.class)
                        .setParameter("data", data)
                        .setParameter("usuario", usuario)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    public List<RegistroSaude> buscarPorPeriodo(Usuario usuario, LocalDate inicio, LocalDate fim) {
        return executeReadOnly(em -> {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.usuario = :usuario AND r.data BETWEEN :inicio AND :fim ORDER BY r.data ASC";

            try {
                return em.createQuery(jpql, RegistroSaude.class)
                        .setParameter("usuario", usuario)
                        .setParameter("inicio", inicio)
                        .setParameter("fim", fim)
                        .getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    public List<RegistroSaude> listarPorUsuario(Usuario usuario) {
        return executeReadOnly(em -> {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.usuario = :usuario ORDER BY r.data ASC";
            try {
                return em.createQuery(jpql, RegistroSaude.class)
                        .setParameter("usuario", usuario)
                        .getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }
}
