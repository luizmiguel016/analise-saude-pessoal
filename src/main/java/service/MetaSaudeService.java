    package service;

    import dao.MetaSaudeDAO;
    import dao.RegistroSaudeDAO;
    import domain.*;

    import java.util.List;

    public class MetaSaudeService {

        private final MetaSaudeDAO metaDAO = new MetaSaudeDAO();
        private final RegistroSaudeDAO registroDAO = new RegistroSaudeDAO();


        public MetaSaude criarMeta(MetaSaude meta) {
            if (meta.getUsuario() == null || meta.getUsuario().getId() == null) {
                throw new IllegalArgumentException("Erro: A meta deve estar associada a um usuário válido.");
            }

            List<MetaSaude> metas = metaDAO.listar("usuario", meta.getUsuario());

            for (MetaSaude existente : metas) {
                if (existente.getClass().equals(meta.getClass())) {
                    throw new IllegalArgumentException("O usuário já possui uma meta desse tipo.");
                }
            }

            try {
                return metaDAO.salvar(meta);
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao salvar meta.");
            }
        }

        public List<MetaSaude> listarMetas(Usuario usuario) {
            validaUsuario(usuario);

            return metaDAO.listar("usuario", usuario);
        }

        public MetaSaude editarMeta(Long id, Number novoAlvo) {

            if (id == null || id < 0) {
                throw new IllegalArgumentException("ID de meta inválido");
            }

            if (novoAlvo == null) {
                throw new IllegalArgumentException("Nova meta não pode ser nulo");
            }

            MetaSaude meta = metaDAO.buscarPorId(id);

            if (meta == null) {
                throw new IllegalArgumentException("Meta não encontrada");
            }

            meta.atualizarMeta(novoAlvo);

            try {
                return metaDAO.editar(meta);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao editar meta.", e);
            }
        }

        public void excluirMetaPorId(Long id) {
            if (id == null || id < 0) {
                throw new IllegalArgumentException("ID de meta inválido.");
            }

            MetaSaude meta = metaDAO.buscarPorId(id);
            if (meta == null) {
                throw new IllegalArgumentException("Meta não encontrada.");
            }

            try {
                metaDAO.remover(meta);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao remover meta.");
            }
        }

        public void avaliarMetasPorRegistro(Usuario usuario) {
            validaUsuario(usuario);

            List<MetaSaude> metas = listarMetas(usuario);
            List<RegistroSaude> registros = new RegistroSaudeService().listarTodos(usuario);

            System.out.println("\n === AVALIAÇÃO DE METAS POR REGISTRO === ");

            for (MetaSaude meta : metas) {
                meta.verificarProgresso(registros);
            }

            for (RegistroSaude registro : registros) {
                System.out.println("\n DATA: " + registro.getData());

                for (MetaSaude meta : metas) {
                    meta.exibirResultado(registro);
                }
            }
        }

        private void validaUsuario(Usuario usuario) {
            if (usuario == null || usuario.getId() == null) {
                throw new IllegalArgumentException("Erro: Usuário inválido ou não identificado.");
            }
        }
    }
