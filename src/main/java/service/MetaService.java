package service;

import dao.MetaSaudeDAO;
import dao.RegistroSaudeDAO;
import domain.MetaSaude;
import domain.RegistroSaude;
import domain.Usuario;

import java.util.List;

public class MetaService {

    private final MetaSaudeDAO metaDAO;
    private final RegistroSaudeDAO registroDAO;

    public MetaService() {
        this.metaDAO = new MetaSaudeDAO();
        this.registroDAO = new RegistroSaudeDAO();
    }

    public void cadastrarMeta(MetaSaude meta) {
        metaDAO.salvar(meta);
    }

    public void atualizarStatusMetas(Usuario usuario) {
        List<MetaSaude> metas = metaDAO.listarPorUsuario(usuario);

        List<RegistroSaude> historico = registroDAO.listarPorUsuario(usuario);

        for (MetaSaude meta : metas) {
            meta.verificarProgresso(historico);

            metaDAO.atualizar(meta);
        }
    }

    public List<MetaSaude> listarMetas(Usuario usuario) {
        return metaDAO.listarPorUsuario(usuario);
    }
}
