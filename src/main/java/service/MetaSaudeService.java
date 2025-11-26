package service;

import dao.MetaSaudeDAO;
import dao.RegistroSaudeDAO;
import domain.MetaPassos;
import domain.MetaSaude;
import domain.RegistroSaude;
import domain.Usuario;

import java.util.List;

public class MetaSaudeService {

    private MetaSaudeDAO metaDAO = new MetaSaudeDAO();
    private RegistroSaudeDAO registroDAO = new RegistroSaudeDAO();

    public void criarMeta(MetaSaude meta) {
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

}
