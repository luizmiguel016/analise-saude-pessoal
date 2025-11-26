package dao;

import domain.MetaSaude;
import domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MetaSaudeDAO extends GenericDAO<MetaSaude, Long> {

    public MetaSaudeDAO() {
        super(MetaSaude.class);
    }

    public List<MetaSaude> listarPorUsuario(Usuario usuario) {
        return executeReadOnly(em -> {
            try {
                return em.createQuery("SELECT m FROM MetaSaude m WHERE m.usuario = :usuario", MetaSaude.class)
                        .setParameter("usuario", usuario)
                        .getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

}
