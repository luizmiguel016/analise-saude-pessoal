package view;

import control.UsuarioControl;
import control.RegistroSaudeControl;
import control.MetaSaudeControl;
import control.AlertaControl;

import domain.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainConsole {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuarioControl usuarioControl = new UsuarioControl();
        RegistroSaudeControl registroControl = new RegistroSaudeControl();
        MetaSaudeControl metaControl = new MetaSaudeControl();
        AlertaControl alertaControl = new AlertaControl();
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Usuario usuarioLogado = null;

        // LOGIN
        while (usuarioLogado == null) {
            System.out.println("\n=== LOGIN ===");
            System.out.println("1 - Entrar");
            System.out.println("2 - Cadastrar novo usuário");
            System.out.print("> ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> {
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    try {
                        usuarioLogado = usuarioControl.autenticar(email, senha);
                        System.out.println("Login realizado!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 2 -> {
                    Usuario u = new Usuario();

                    System.out.print("Nome: ");
                    u.setNome(sc.nextLine());

                    System.out.print("Email: ");
                    u.setEmail(sc.nextLine());

                    System.out.print("Senha: ");
                    u.setSenha(sc.nextLine());

                    System.out.print("CPF: ");
                    u.setCpf(sc.nextLine());

                    System.out.print("BPM mínimo alerta: ");
                    u.setBpmMinAlerta(Integer.parseInt(sc.nextLine()));

                    System.out.print("BPM máximo alerta: ");
                    u.setBpmMaxAlerta(Integer.parseInt(sc.nextLine()));

                    try {
                        usuarioControl.cadastrar(u);
                        System.out.println("Cadastro concluído. Faça login.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                default -> System.out.println("Opção inválida.");
            }
        }

        // MENU PRINCIPAL
        while (true) {

            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Registrar Saúde");
            System.out.println("2 - Listar Registros");
            System.out.println("3 - Listar Alertas");
            System.out.println("4 - Avaliar Metas");
            System.out.println("5 - Criar Meta Passos");
            System.out.println("6 - Criar Meta Sono");
            System.out.println("7 - Editar Meta");
            System.out.println("8 - Excluir Meta");
            System.out.println("9 - Listar últimos dias");
            System.out.println("0 - Sair");
            System.out.print("> ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> {
                    RegistroSaude registro = new RegistroSaude();
                    registro.setUsuario(usuarioLogado);

                    System.out.print("Data (DD-MM-AAAA): ");
                    try {
                        LocalDate data = LocalDate.parse(sc.nextLine(), formatoBR);
                        registro.setData(data);
                    } catch (DateTimeException e) {
                        System.out.println("Data inválida. Use o formato DD-MM-AAA");
                        break;
                    }

                    System.out.print("Passos: ");
                    registro.setPassos(Integer.parseInt(sc.nextLine()));

                    System.out.print("BPM médio: ");
                    registro.setBpmMedio(Integer.parseInt(sc.nextLine()));

                    System.out.print("Horas de sono: ");
                    registro.setHorasSono(Double.parseDouble(sc.nextLine()));

                    System.out.print("Kcal: ");
                    registro.setKcal(Integer.parseInt(sc.nextLine()));

                    registroControl.registrar(registro);
                    System.out.println("Registro salvo!");
                }

                case 2 -> {
                    List<RegistroSaude> lista = registroControl.listar(usuarioLogado);
                    for (RegistroSaude registro : lista) {
                        System.out.println(registro);
                    }
                }

                case 3 -> {
                    List<Alerta> alertas = alertaControl.listar(usuarioLogado);
                    for (Alerta alerta : alertas) {
                        System.out.println(alerta);
                    }
                }

                case 4 -> {
                    metaControl.avaliarMetas(usuarioLogado);
                }

                case 5 -> {
                    try {
                        System.out.print("Meta de passos: ");
                        int alvo = Integer.parseInt(sc.nextLine());
                        metaControl.criar(new MetaPassos(usuarioLogado, alvo));
                        System.out.println("Meta de passos criada!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 6 -> {
                    try {
                        System.out.print("Meta de horas de sono: ");
                        double alvo = Double.parseDouble(sc.nextLine());
                        metaControl.criar(new MetaMediaSono(usuarioLogado, alvo));
                        System.out.println("Meta de sono criada!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 7 -> {
                    List<MetaSaude> metas = metaControl.listar(usuarioLogado);

                    if (metas.isEmpty()) {
                        System.out.println("Nenhuma meta cadastrada.");
                        break;
                    }

                    System.out.println("Metas de usuário:");
                    for (MetaSaude meta : metas) {
                        System.out.println(meta);
                    }

                    System.out.print("ID da meta: ");
                    Long id = Long.parseLong(sc.nextLine());

                    System.out.print("Novo valor: ");
                    Double novoValor = Double.parseDouble(sc.nextLine());

                    try {
                        metaControl.atualizar(id, novoValor);
                        System.out.println("Meta atualizada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 8 -> {
                    List<MetaSaude> metas = metaControl.listar(usuarioLogado);
                    if (metas.isEmpty()) {
                        System.out.println("Nenhuma meta cadastrada.");
                        break;
                    }

                    System.out.println("Metas do usuário:");
                    for (MetaSaude m : metas) {
                        System.out.println(m);
                    }

                    try {
                        System.out.print("ID da meta a excluir: ");
                        Long idMeta = Long.parseLong(sc.nextLine());
                        metaControl.excluir(idMeta);
                        System.out.println("Meta removida!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 9 -> {
                    System.out.print("Quantos dias (ex: 7): ");
                    int dias = Integer.parseInt(sc.nextLine());
                    List<RegistroSaude> lista = registroControl.buscarHistorico(usuarioLogado, dias);
                    for (RegistroSaude reg : lista) {
                        System.out.println(reg);
                    }
                }

                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
