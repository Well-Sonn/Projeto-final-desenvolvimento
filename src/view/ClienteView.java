package view;

import controller.UsuarioController;
import controller.AmbienteController;
import controller.ReservaController;
import model.entity.Cliente;
import model.entity.Ambiente;
import model.entity.Reserva;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private UsuarioController usuarioController;
    private AmbienteController ambienteController;
    private ReservaController reservaController;

    public ClienteView(UsuarioController usuarioController, AmbienteController ambienteController, ReservaController reservaController) {
        this.usuarioController = usuarioController;
        this.ambienteController = ambienteController;
        this.reservaController = reservaController;
    }

    public ClienteView(Cliente cliente) {
        //TODO Auto-generated constructor stub
    }

    public void showClienteMenu(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu Cliente:");
            System.out.println("1. Listar Ambientes Disponíveis");
            System.out.println("2. Reservar Ambiente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (choice) {
                case 1:
                    List<Ambiente> ambientes = ambienteController.getAllAmbientes();
                    for (Ambiente a : ambientes) {
                        if (a.isAtivo()) {
                            System.out.println(a.getId() + " - " + a.getNome());
                            for (String horario : a.getHorarios()) {
                                System.out.print(horario);
                                Reserva reserva = reservaController.getAllReservas().stream()
                                        .filter(r -> r.getAmbienteId().equals(a.getId()) && r.getHorario().equals(horario))
                                        .findFirst()
                                        .orElse(null);
                                if (reserva != null) {
                                    System.out.println(" - Reservado por " + usuarioController.getAllUsuarios().stream()
                                            .filter(u -> u.getId().equals(reserva.getUsuarioId()))
                                            .findFirst()
                                            .orElse(null).getNome());
                                } else {
                                    System.out.println(" - Livre");
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("ID do Ambiente: ");
                    String ambienteId = scanner.nextLine();
                    Ambiente ambiente = ambienteController.getAllAmbientes().stream()
                            .filter(a -> a.getId().equals(ambienteId))
                            .findFirst()
                            .orElse(null);
                    if (ambiente != null && ambiente.isAtivo()) {
                        System.out.print("Horário: ");
                        String horario = scanner.nextLine();
                        Reserva reserva = new Reserva(java.util.UUID.randomUUID().toString(), cliente.getId(), ambienteId, horario);
                        reservaController.addReserva(reserva);
                    } else {
                        System.out.println("Ambiente não encontrado ou inativo.");
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
