package view;
import java.util.Scanner;

import controller.UsuarioController;
import model.abstracts.AbstractUsuario;
import model.entity.Administrador;
import model.entity.Cliente;

public class UsuarioView {
    private UsuarioController usuarioController;
    private Scanner scanner;

    public UsuarioView() {
        this.usuarioController = new UsuarioController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    login();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Tipo de Usuário: 1. Administrador 2. Cliente");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        AbstractUsuario usuario;
        if (tipo == 1) {
            usuario = new Administrador(nome, email, senha);
        } else {
            usuario = new Cliente(nome, email, senha);
        }

        usuarioController.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        AbstractUsuario usuario = usuarioController.login(email, senha);
        if (usuario != null) {
            System.out.println("Login bem-sucedido!");
            usuario.exibirInfo();
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
}