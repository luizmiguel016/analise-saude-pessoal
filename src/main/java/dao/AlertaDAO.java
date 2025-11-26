package dao;

import domain.Alerta;
import domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AlertaDAO extends GenericDAO<Alerta, Long> {

    public AlertaDAO() {
        super(Alerta.class);
    }

    public List<Alerta> listarPorUsuario(Usuario usuario) {
        return executeReadOnly(em -> {
            try {
                return em.createQuery("SELECT a FROM Alerta a WHERE a.usuario = :usuario ORDER BY a.dataHora DESC",
                                Alerta.class)
                        .setParameter("usuario", usuario)
                        .getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }
}