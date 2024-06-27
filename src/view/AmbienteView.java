package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.AmbienteController;
import model.entity.Ambiente;
import model.entity.Usuario;
import util.AppLogger;

public class AmbienteView {

    // Método estático para verificar se o formato do horário é válido
    public static boolean validarHorario(String horario) {
        // Expressão regular para o formato HH:mm-HH:mm
        String regra = "\\d{2}:\\d{2}-\\d{2}:\\d{2}";
        return Pattern.matches(regra, horario);
    }

    private Usuario usuarioLogado;

    public AmbienteView(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void gerirAmbientes(Scanner scanner) {
        AmbienteController ambienteController = new AmbienteController();

        while (true) {
            System.out.println("\n=============================================================");
            System.out.println("              GERIR AMBIENTES            ");
            System.out.println("=============================================================\n");
            System.out.println("1 - CADASTRAR NOVO AMBIENTE");
            System.out.println("2 - LISTAR AMBIENTES");
            System.out.println("3 - ALTERAR AMBIENTE");
            System.out.println("4 - DELETAR AMBIENTE");
            System.out.println("5 - VOLTAR");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome do ambiente: ");
                    String nome = scanner.nextLine();
                    List<String> horariosList = new ArrayList<>();
                    boolean adicionarMaisHorarios = true;

                    while (adicionarMaisHorarios) {
                        boolean horarioValido = false;
                        String horario = "";

                        while (!horarioValido) {
                            System.out.print("Digite o horário disponível (ex: 09:00-13:00): ");
                            horario = scanner.nextLine();

                            if (validarHorario(horario)) {
                                horarioValido = true;
                                horariosList.add(horario);
                            } else {
                                System.out.println("Formato de horário inválido. Tente novamente.");
                            }
                        }

                        System.out.println("Deseja cadastrar mais algum horário?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int adicionarOutroHorario = scanner.nextInt();
                        scanner.nextLine();  // Limpar o buffer

                        if (adicionarOutroHorario != 1) {
                            adicionarMaisHorarios = false;
                        }
                    }

                    String horarios = String.join(", ", horariosList);
                    boolean sucessoCadastro = ambienteController.cadastrarNovoAmbiente(nome, horarios);
                    if (sucessoCadastro) {
                        AppLogger.info(usuarioLogado.getEmail(), "Cadastrou novo ambiente: " + nome);
                        System.out.println("\nAmbiente cadastrado com sucesso!");
                    } else {
                        AppLogger.error(usuarioLogado.getEmail(), "Erro ao cadastrar ambiente: " + nome);
                        System.out.println("\nErro ao cadastrar ambiente. Tente novamente.");
                    }
                    break;

                case 2:
                    System.out.println("\n=============================================================");
                    System.out.println("            LISTA DE AMBIENTES           ");
                    System.out.println("=============================================================");
                    List<Ambiente> ambientes = ambienteController.listarAmbientes();
                    for (Ambiente ambiente : ambientes) {
                        System.out.println("ID: " + ambiente.getId() + ", Nome: " + ambiente.getNome() + ", Horários: " + ambiente.getHorarios());
                    }
                    AppLogger.info(usuarioLogado.getEmail(), "Listou ambientes.");
                    System.out.println("=============================================================\n");
                    break;

                case 3:
                    System.out.print("\nDigite o ID do ambiente a ser alterado: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer
                    System.out.print("Digite o novo nome do ambiente: ");
                    String novoNome = scanner.nextLine();
                    List<String> novosHorariosList = new ArrayList<>();
                    boolean adicionarMaisHorariosCase3 = true;

                    while (adicionarMaisHorariosCase3) {
                        boolean horarioValido = false;
                        String novoHorario = "";

                        while (!horarioValido) {
                            System.out.print("Digite o novo horário disponível (ex: 09:00-13:00): ");
                            novoHorario = scanner.nextLine();

                            if (validarHorario(novoHorario)) {
                                horarioValido = true;
                                novosHorariosList.add(novoHorario);
                            } else {
                                System.out.println("Formato de horário inválido. Tente novamente.");
                            }
                        }

                        System.out.println("Deseja cadastrar mais algum horário?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        int adicionarOutroHorario = scanner.nextInt();
                        scanner.nextLine();  // Limpar o buffer

                        if (adicionarOutroHorario != 1) {
                            adicionarMaisHorariosCase3 = false;
                        }
                    }

                    String novosHorarios = String.join(", ", novosHorariosList);
                    boolean sucessoAlteracao = ambienteController.alterarAmbiente(idAlterar, novoNome, novosHorarios);
                    if (sucessoAlteracao) {
                        AppLogger.info(usuarioLogado.getEmail(), "Alterou ambiente ID: " + idAlterar);
                        System.out.println("\nAmbiente alterado com sucesso!");
                    } else {
                        AppLogger.error(usuarioLogado.getEmail(), "Erro ao alterar ambiente ID: " + idAlterar);
                        System.out.println("\nErro ao alterar ambiente. Tente novamente.");
                    }
                    break;

                case 4:
                    System.out.print("\nDigite o ID do ambiente a ser deletado: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    Ambiente ambienteParaDeletar = ambienteController.getAmbienteById(idDeletar);
                    if (ambienteParaDeletar != null) {
                        System.out.println("Você deseja realmente deletar o ambiente com ID: " + idDeletar + ", Nome: " + ambienteParaDeletar.getNome() + "?");
                        System.out.println("1 - SIM");
                        System.out.println("2 - NÃO");
                        int confirmacao = scanner.nextInt();
                        scanner.nextLine();  // Limpar o buffer

                        if (confirmacao == 1) {
                            boolean sucessoDelecao = ambienteController.deletarAmbiente(idDeletar);
                            if (sucessoDelecao) {
                                AppLogger.info(usuarioLogado.getEmail(), "Deletou ambiente ID: " + idDeletar);
                                System.out.println("\nAmbiente deletado com sucesso!");
                            } else {
                                AppLogger.error(usuarioLogado.getEmail(), "Erro ao deletar ambiente ID: " + idDeletar);
                                System.out.println("\nErro ao deletar ambiente. Tente novamente.");
                            }
                        } else {
                            System.out.println("\nOperação cancelada.");
                            AppLogger.info(usuarioLogado.getEmail(), "Cancelou a deleção do ambiente ID: " + idDeletar);
                        }
                    } else {
                        System.out.println("\nAmbiente não encontrado.");
                        AppLogger.warn(usuarioLogado.getEmail(), "Tentativa de deletar ambiente não encontrado com ID: " + idDeletar);
                    }
                    break;

                case 5:
                    System.out.println("\nRetornando ao menu principal.");
                    return;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }
}
