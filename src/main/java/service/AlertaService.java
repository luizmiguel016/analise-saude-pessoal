package service;

import dao.AlertaDAO;
import domain.*;

import java.util.List;
import java.util.Optional;

public class AlertaService {

    private final AlertaDAO alertaDAO;
    private final ArvoreDeDecisaoAlerta arvore;

    public AlertaService() {
        this.alertaDAO = new AlertaDAO();
        this.arvore = new ArvoreDeDecisaoAlerta();
    }

    public Optional<Alerta> processarRegistro(RegistroSaude registro) {
        Optional<Alerta> resultado = arvore.avaliar(registro);

        if (resultado.isEmpty()) {
            return Optional.empty();
        }

        Alerta alerta = resultado.get();

        try {
            alertaDAO.salvar(alerta);
            System.out.println("ALERTA GERADO: " + alerta.getMensagem());
            return Optional.of(alerta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar alerta automático", e);
        }
    }

    public List<Alerta> listarHistorico(Usuario usuario) {
        return alertaDAO.listar("usuario", usuario);
    }

}