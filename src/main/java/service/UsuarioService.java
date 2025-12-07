package service;

import dao.UsuarioDAO;
import domain.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario cadastrar(Usuario usuario) {

        if (usuarioDAO.buscarUsuarioPorCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (usuarioDAO.buscarUsuarioPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        try {
            return usuarioDAO.salvar(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível cadastrar o usuário.");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        validarId(id);

        try {
            return usuarioDAO.buscarPorId(id);
        } catch (RuntimeException e) { // NotFoundException
            throw new RuntimeException("Não foi possível encontrar o usuário.", e);
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.buscarTodos();
    }

    public void removerUsuario(Long id) {
        validarId(id);

        try {
            usuarioDAO.removerPorId(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível remover o usuário.", e);
        }

    }

    public void alterarSenha(Long id, String novaSenha) {
        validarId(id);

        try {
            Usuario usuario = usuarioDAO.buscarPorId(id);
            usuario.setSenha(novaSenha);
            usuarioDAO.editar(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível alterar a senha.", e);
        }
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Usuário ou senha inválidos.");
        }
        return usuario;
    }

    private void validarId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID deve ser válido.");
        }
    }
}