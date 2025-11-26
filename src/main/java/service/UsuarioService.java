package service;

import dao.UsuarioDAO;
import domain.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public void excluirUsuario(Long id) {
        usuarioDAO.removerPorId(id);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuarioDAO.buscarPorCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("Erro: CPF já cadastrado: " + usuario.getCpf());
        }
        if (usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Erro: E-mail já cadastrado: " + usuario.getEmail());
        }
        usuarioDAO.salvar(usuario);
    }

    public void alterarSenha(Long idUsuario, String novaSenha) {
        Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
        if (usuario == null) throw new IllegalArgumentException("Usuário não encontrado.");

        if (usuario.getSenha().equals(novaSenha)) {
            throw new IllegalArgumentException("A nova senha não pode ser igual à anterior.");
        }
        usuario.setSenha(novaSenha);
        usuarioDAO.atualizar(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        throw new IllegalArgumentException("Credenciais inválidas.");
    }
}