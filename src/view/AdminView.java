package view;

import controller.UsuarioController;
import controller.AmbienteController;
import controller.ReservaController;
import model.entity.Administrador;
import model.entity.Cliente;
import model.entity.Usuario;
import model.entity.Ambiente;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private UsuarioController usuarioController;
    private AmbienteController ambienteController;
    private ReservaController reservaController;

    public AdminView(UsuarioController usuarioController, AmbienteController ambienteController, ReservaController reservaController) {
        this.usuarioController = usuarioController;
        this.ambienteController = ambienteController;
        this.reservaController = reservaController;
    }

    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu Administrador:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Alterar Usuário");
            System.out.println("3. Listar Usuários");
            System.out.println("4. Deletar Usuário");
            System.out.println("5. Cadastrar Ambiente");
            System.out.println("6. Alterar Ambiente");
            System.out.println("7. Listar Ambientes");
            System.out.println("8. Deletar Ambiente");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (choice) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Tipo (1 para Administrador, 2 para Cliente): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline

                    String id = java.util.UUID.randomUUID().toString();
                    Usuario usuario;
                    if (tipo == 1) {
                        usuario = new Administrador(id, nome, email, senha);
                    } else {
                        usuario = new Cliente(id, nome, email, senha);
                    }
                    usuarioController.addUsuario(usuario);
                    break;
                case 2:
                    System.out.print("ID do Usuário a ser alterado: ");
                    String userId = scanner.nextLine();
                    Usuario usuarioExistente = usuarioController.getAllUsuarios().stream()
                            .filter(u -> u.getId().equals(userId))
                            .findFirst()
                            .orElse(null);
                    if (usuarioExistente != null) {
                        System.out.print("Novo Nome: ");
                        usuarioExistente.setNome(scanner.nextLine());
                        System.out.print("Novo Email: ");
                        usuarioExistente.setEmail(scanner.nextLine());
                        System.out.print("Nova Senha: ");
                        usuarioExistente.setSenha(scanner.nextLine());
                        usuarioController.updateUsuario(usuarioExistente);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 3:
                    List<Usuario> usuarios = usuarioController.getAllUsuarios();
                    for (Usuario u : usuarios) {
                        System.out.println(u.getId() + " - " + u.getNome() + " (" + (u instanceof Administrador ? "Administrador" : "Cliente") + ")");
                    }
                    break;
                case 4:
                    System.out.print("ID do Usuário a ser deletado: ");
                    userId = scanner.nextLine();
                    usuarioController.deleteUsuario(userId);
                    break;
                case 5:
                    System.out.print("Nome do Ambiente: ");
                    String nomeAmbiente = scanner.nextLine();
                    System.out.print("Horários (separados por vírgula): ");
                    String horariosStr = scanner.nextLine();
                    List<String> horarios = List.of(horariosStr.split(","));
                    boolean ativo = true;
                    String ambienteId = java.util.UUID.randomUUID().toString();
                    Ambiente ambiente = new Ambiente(ambienteId, nomeAmbiente, horarios, ativo);
                    ambienteController.addAmbiente(ambiente);
                    break;
                case 6:
                    System.out.print("ID do Ambiente a ser alterado: ");
                    String ambienteIdAlt = scanner.nextLine();
                    Ambiente ambienteExistente = ambienteController.getAllAmbientes().stream()
                            .filter(a -> a.getId().equals(ambienteIdAlt))
                            .findFirst()
                            .orElse(null);
                    if (ambienteExistente != null) {
                        System.out.print("Novo Nome do Ambiente: ");
                        ambienteExistente.setNome(scanner.nextLine());
                        System.out.print("Novos Horários (separados por vírgula): ");
                        horariosStr = scanner.nextLine();
                        ambienteExistente.setHorarios(List.of(horariosStr.split(",")));
                        ambienteController.updateAmbiente(ambienteExistente);
                    } else {
                        System.out.println("Ambiente não encontrado.");
                    }
                    break;
                case 7:
                    List<Ambiente> ambientes = ambienteController.getAllAmbientes();
                    for (Ambiente a : ambientes) {
                        System.out.println(a.getId() + " - " + a.getNome() + " (" + (a.isAtivo() ? "Ativo" : "Inativo") + ")");
                    }
                    break;
                case 8:
                    System.out.print("ID do Ambiente a ser deletado: ");
                    ambienteIdAlt = scanner.nextLine();
                    ambienteController.deleteAmbiente(ambienteIdAlt);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
