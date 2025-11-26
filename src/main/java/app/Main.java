package app;

import domain.Usuario;
import service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();

        try {
            System.out.println("Cadastrando usuário...");
            Usuario u = new Usuario("Luiz", "luiz@teste.com", "123456", "11122233344", 50, 120);
            service.cadastrarUsuario(u);
            System.out.println("Sucesso!");

            System.out.println("Tentando logar...");
            Usuario logado = service.autenticar("luiz@teste.com", "123456");
            System.out.println("Logado: " + logado.getNome());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}