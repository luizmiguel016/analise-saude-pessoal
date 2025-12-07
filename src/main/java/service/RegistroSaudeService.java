package service;

import dao.RegistroSaudeDAO;
import domain.RegistroSaude;
import domain.Usuario;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

public class RegistroSaudeService {

    private final RegistroSaudeDAO registroDAO = new RegistroSaudeDAO();

    public RegistroSaude registrarRegistro(RegistroSaude registro) {
        validarMetricasNaoNegativas(registro);
        validarDataRegistro(registro);

        try {
            RegistroSaude registroExistente = registroDAO.buscarPorDataEUsuario(registro.getData(), registro.getUsuario());

            if (registroExistente != null) {
                throw new IllegalArgumentException("Já existe registro nessa data.");
            }

        } catch (NoResultException e) {

        }

        try {
            RegistroSaude registroSalvo = registroDAO.salvar(registro);

            AlertaService alertaService = new AlertaService();
            alertaService.processarRegistro(registroSalvo);

            return registroSalvo;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o registro: ", e);
        }
    }

    public RegistroSaude buscarPorId(Long id) {
        try {
            return registroDAO.buscarPorId(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<RegistroSaude> listarTodos(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        return registroDAO.listar("usuario", usuario);
    }

    public List<RegistroSaude> buscarHistoricoRecente(Usuario usuario, int dias) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        if (dias < 0) {
            throw new IllegalArgumentException("Número de dias inválido.");
        }

        LocalDate fim = LocalDate.now();
        LocalDate inicio = fim.minusDays(dias);

        return registroDAO.buscarPorPeriodo(usuario, inicio, fim);
    }

    public RegistroSaude atualizarRegistro(RegistroSaude registro) {
        validarMetricasNaoNegativas(registro);
        try {
            registroDAO.editar(registro);
            return registro;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }
    }

    public void removerRegistro(Long id) {
        RegistroSaude registro = registroDAO.buscarPorId(id);
        if (registro == null) {
            throw new IllegalArgumentException("ID não existe.");
        }
        try {
            registroDAO.remover(registro);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível remover o registro.");
        }
    }

    private void validarMetricasNaoNegativas(RegistroSaude r) {
        if (r.getPassos() < 0 || r.getHorasSono() < 0 || r.getKcal() < 0 || r.getBpmMedio() < 0) {
            throw new IllegalArgumentException("Métricas não podem ser negativas.");
        }
    }

    private void validarDataRegistro(RegistroSaude registro) {
        LocalDate data = registro.getData();

        if (data == null) {
            throw new IllegalArgumentException("A data do registro não pode ser nula.");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Não é permitido registrar dados em uma data futura.");
        }
    }
}