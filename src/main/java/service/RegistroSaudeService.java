package service;

import dao.RegistroSaudeDAO;
import domain.RegistroSaude;
import domain.Usuario;

import java.time.LocalDate;
import java.util.List;

public class RegistroSaudeService {

    private final RegistroSaudeDAO registroDAO;

    public RegistroSaudeService() {
        this.registroDAO = new RegistroSaudeDAO();
    }

    public void registrarMetrica(RegistroSaude registro) {
        validarMetricasNaoNegativas(registro);

        RegistroSaude existente = registroDAO.buscarPorDataEUsuario(registro.getData(), registro.getUsuario());

        if (existente != null) {
            throw new IllegalArgumentException("Erro: O usuário já possui um registro para a data " + registro.getData());
        }

        registroDAO.salvar(registro);
    }

    public RegistroSaude buscarPorId(Long id) {
        return registroDAO.buscarPorId(id);
    }

    public List<RegistroSaude> listarTodos(Usuario usuario) {
        return registroDAO.listarPorUsuario(usuario);
    }

    public List<RegistroSaude> buscarHistoricoRecente(Usuario usuario, int dias) {
        LocalDate fim = LocalDate.now();
        LocalDate inicio = fim.minusDays(dias);

        return registroDAO.buscarPorPeriodo(usuario, inicio, fim);
    }

    public void atualizarRegistro(RegistroSaude registro) {
        validarMetricasNaoNegativas(registro);
        registroDAO.atualizar(registro);
    }

    public void removerRegistro(Long idRegistro) {
        registroDAO.removerPorId(idRegistro);
    }

    private void validarMetricasNaoNegativas(RegistroSaude r) {
        if (r.getPassos() < 0) throw new IllegalArgumentException("Passos não podem ser negativos.");
        if (r.getHorasSono() < 0) throw new IllegalArgumentException("Horas de sono não podem ser negativas.");
        if (r.getKcal() < 0) throw new IllegalArgumentException("Calorias não podem ser negativas.");
        if (r.getBpmMedio() < 0) throw new IllegalArgumentException("BPM não pode ser negativo.");
    }
}