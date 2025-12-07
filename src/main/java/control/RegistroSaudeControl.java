package control;

import domain.RegistroSaude;
import domain.Usuario;
import service.RegistroSaudeService;

import java.util.List;

public class RegistroSaudeControl {
    private final RegistroSaudeService registroService = new RegistroSaudeService();

    public void registrar(RegistroSaude registroSaude) {
        registroService.registrarRegistro(registroSaude);
    }

    public RegistroSaude buscarPorId(Long id) {
        return registroService.buscarPorId(id);
    }

    public List<RegistroSaude> listar(Usuario usuario) {
        return registroService.listarTodos(usuario);
    }

    public List<RegistroSaude> buscarHistorico(Usuario usuario, int dias) {
        return registroService.buscarHistoricoRecente(usuario, dias);
    }

    public void atualizar(RegistroSaude registro) {
        registroService.atualizarRegistro(registro);
    }

    public void remover(Long id) {
        registroService.removerRegistro(id);
    }
}
