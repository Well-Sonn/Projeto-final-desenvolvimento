package view;

import controller.AmbienteController;
import controller.ReservaController;
import controller.UsuarioController;
import model.entity.Administrador;
import model.entity.Cliente;
import model.entity.Usuario;

import java.util.Scanner;

public class MainView {
    private UsuarioController usuarioController;
    private AmbienteController ambienteController;
    private ReservaController reservaController;
    private Scanner scanner;

    public MainView(UsuarioController usuarioController, AmbienteController ambienteController, ReservaController reservaController) {
        this.usuarioController = usuarioController;
        this.ambienteController = ambienteController;
        this.reservaController = reservaController;
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("BEM VINDO AO RESERVE.AI! POR FAVOR ENTRE COM SUA CREDENCIAL");
            System.out.println("1. Login");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (choice) {
                case 1:
                    showLoginMenu();
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    public void showLoginMenu() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario usuario = usuarioController.getAllUsuarios().stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (usuario instanceof Administrador) {
            AdminView adminView = new AdminView(usuarioController, ambienteController, reservaController);
            adminView.showAdminMenu();
        } else if (usuario instanceof Cliente) {
            ClienteView clienteView = new ClienteView(usuarioController, ambienteController, reservaController);
            clienteView.showClienteMenu((Cliente) usuario);
        } else {
            System.out.println("Credenciais inválidas.");
        }
    }
}
