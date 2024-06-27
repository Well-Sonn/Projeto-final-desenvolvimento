package view;

import java.util.Scanner;
import controller.AmbienteController;
import model.entity.Cliente;
import model.entity.Ambiente;
import util.AppLogger;

public class ReservaView {

    private Cliente usuarioLogado;

    public ReservaView(Cliente usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void reservarAmbiente() {
        AmbienteController ambienteController = new AmbienteController();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {  // Loop para permitir reservas múltiplas sem sair do menu
                System.out.println("\n=============================================================");
                System.out.println("      SISTEMA DE RESERVA DE AMBIENTES    ");
                System.out.println("=============================================================");
                System.out.println();

                System.out.print("Digite o ID do ambiente que deseja reservar \n(ou digite 0 para voltar ao menu): ");
                int idAmbiente = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                if (idAmbiente == 0) {
                    UsuarioView usuarioView = new UsuarioView(usuarioLogado);
                    usuarioView.menuCliente(scanner);
                    break;
                }

                Ambiente ambiente = ambienteController.getAmbienteById(idAmbiente);

                if (ambiente != null) {
                    String horario = "";

                    while (true) {  // Loop para permitir escolha de horário até ser válido
                        System.out.print("Digite o horário desejado (ex: 09:00-13:00): ");
                        horario = scanner.nextLine();
                        System.out.println();

                        // Validar o formato do horário usando o HorarioValidator
                        if (AmbienteView.validarHorario(horario)) {
                            boolean sucesso = usuarioLogado.reservarAmbiente(ambiente, horario);

                            if (sucesso) {
                                AppLogger.info(usuarioLogado.getEmail(), "Reservou ambiente ID: " + idAmbiente + " no horário: " + horario);
                                System.out.println("\n========================================");
                                System.out.println("     Reserva realizada com sucesso!     ");
                                System.out.println("========================================");
                                System.out.println();
                                break;  // Sair do loop de horário após sucesso
                            } else {
                                AppLogger.error(usuarioLogado.getEmail(), "Erro ao reservar ambiente ID: " + idAmbiente + " no horário: " + horario);
                                System.out.println("\n=================================================");
                                System.out.println("    HORÁRIO RESERVADO. ESCOLHA OUTRO HORÁRIO.    ");
                                System.out.println("=================================================");
                                System.out.println();
                                break;  // Sair do loop de horário após falha
                            }
                        } else {
                            System.out.println("Formato de horário inválido. Tente novamente.");
                        }
                    }
                } else {
                    AppLogger.warn(usuarioLogado.getEmail(), "Tentativa de reservar ambiente não encontrado com ID: " + idAmbiente);
                    System.out.println("\n=============================================================");
                    System.out.println("       Ambiente não encontrado.       ");
                    System.out.println("=============================================================");
                    System.out.println();
                }
            }
        }
    }
}
