package control;

import domain.MetaSaude;
import domain.Usuario;
import service.MetaSaudeService;

import java.util.List;

public class MetaSaudeControl {

    private final MetaSaudeService metaService = new MetaSaudeService();

    public MetaSaude criar(MetaSaude meta) {
        return metaService.criarMeta(meta);
    }

    public List<MetaSaude> listar(Usuario usuario) {
        return metaService.listarMetas(usuario);
    }

    public void atualizar(Long id, Number novoAlvo) {
        metaService.editarMeta(id, novoAlvo);
    }

    public void excluir(Long id) {
        metaService.excluirMetaPorId(id);
    }

    public void avaliarMetas(Usuario usuario) {
        metaService.avaliarMetasPorRegistro(usuario);
    }
}