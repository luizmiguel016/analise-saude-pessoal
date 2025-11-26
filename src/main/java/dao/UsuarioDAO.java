package dao;

import domain.Usuario;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public List<Usuario> buscarPorNome(String parteDoNome) {
        return executeReadOnly(em -> {
            String jpql = "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(:nome)";
            try {
                return em.createQuery(jpql, Usuario.class)
                        .setParameter("nome", "%" + parteDoNome + "%")
                        .getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    public Usuario buscarPorEmail(String email) {
        return executeReadOnly(em -> {
            try {
                return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                        .setParameter("email", email)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    public Usuario buscarPorCpf(String cpf) {
        return executeReadOnly(em -> {
            try {
                return em.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf", Usuario.class)
                        .setParameter("cpf", cpf)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }
}