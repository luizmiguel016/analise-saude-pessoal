package control;

import domain.Usuario;
import service.UsuarioService;

import java.util.List;

public class UsuarioControl {

    private final UsuarioService usuarioService = new UsuarioService();

    public Usuario cadastrar(Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    public void remover(Long id) {
        usuarioService.removerUsuario(id);
    }

    public void alterarSenha(Long id, String novaSenha) {
        usuarioService.alterarSenha(id, novaSenha);
    }

    public Usuario autenticar(String email, String senha) {
        return usuarioService.autenticar(email, senha);
    }
}