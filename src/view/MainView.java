package view;

import java.util.List;
import java.util.Scanner;

import controller.AmbienteController;
import controller.UsuarioController;
import controller.ReservaController;
import model.entity.Administrador;
import model.entity.Ambiente;
import model.entity.Cliente;
import model.entity.Reserva;
import model.entity.Usuario;
import util.AppLogger;

public class MainView {

    private Usuario usuarioLogado;

    public void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController();

        System.out.println("Bem-vindo ao sistema de reserva de ambientes!");
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = usuarioController.login(email, senha);

        if (usuarioLogado != null) {
            AppLogger.info(usuarioLogado.getEmail(), "Login realizado com sucesso.");
            if (usuarioLogado instanceof Administrador) {
                System.out.println("Bem-vindo, Administrador!");
                while (true) {
                    System.out.println("1- GERIR AMBIENTE");
                    System.out.println("2- GERIR USUÁRIOS");
                    System.out.println("3- SAIR");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    if (opcao == 1) {
                        gerirAmbientes(scanner);
                    } else if (opcao == 2) {
                        gerirUsuarios(scanner, usuarioController);
                    } else if (opcao == 3) {
                        AppLogger.info(usuarioLogado.getEmail(), "Logout realizado.");
                        break;
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            } else if (usuarioLogado instanceof Cliente) {
                System.out.println("Bem-vindo, Cliente!");
                while (true) {
                    System.out.println("1- VER AMBIENTES DISPONÍVEIS");
                    System.out.println("2- RESERVAR AMBIENTE");
                    System.out.println("3- LISTAR MINHAS RESERVAS");
                    System.out.println("4- SAIR");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    if (opcao == 1) {
                        listarAmbientesDisponiveis();
                    } else if (opcao == 2) {
                        reservarAmbiente((Cliente) usuarioLogado);
                    } else if (opcao == 3) {
                        listarMinhasReservas((Cliente) usuarioLogado);
                    } else if (opcao == 4) {
                        AppLogger.info(usuarioLogado.getEmail(), "Logout realizado.");
                        break;
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        } else {
            AppLogger.error(email, "Login inválido.");
            System.out.println("Login inválido. Tente novamente.");
        }

        scanner.close();
    }

    private void gerirAmbientes(Scanner scanner) {
        AmbienteController ambienteController = new AmbienteController();

        while (true) {
            System.out.println("1- CADASTRAR NOVO AMBIENTE");
            System.out.println("2- LISTAR AMBIENTES");
            System.out.println("3- ALTERAR AMBIENTE");
            System.out.println("4- DELETAR AMBIENTE");
            System.out.println("5- VOLTAR");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (opcao == 1) {
                System.out.print("Digite o nome do ambiente: ");
                String nome = scanner.nextLine();
                System.out.print("Digite os horários disponíveis (ex: 09:00-13:00): ");
                String horarios = scanner.nextLine();

                boolean sucesso = ambienteController.cadastrarNovoAmbiente(nome, horarios);
                if (sucesso) {
                    AppLogger.info(usuarioLogado.getEmail(), "Cadastrou novo ambiente: " + nome);
                    System.out.println("Ambiente cadastrado com sucesso!");
                } else {
                    AppLogger.error(usuarioLogado.getEmail(), "Erro ao cadastrar ambiente: " + nome);
                    System.out.println("Erro ao cadastrar ambiente. Tente novamente.");
                }
            } else if (opcao == 2) {
                List<Ambiente> ambientes = ambienteController.listarAmbientes();
                for (Ambiente ambiente : ambientes) {
                    System.out.println("ID: " + ambiente.getId() + ", Nome: " + ambiente.getNome() + ", Horários: " + ambiente.getHorarios());
                }
                AppLogger.info(usuarioLogado.getEmail(), "Listou ambientes.");
            } else if (opcao == 3) {
                System.out.print("Digite o ID do ambiente a ser alterado: ");
                int id = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer
                System.out.print("Digite o novo nome do ambiente: ");
                String nome = scanner.nextLine();
                System.out.print("Digite os novos horários disponíveis (ex: 09:00-13:00): ");
                String horarios = scanner.nextLine();

                boolean sucesso = ambienteController.alterarAmbiente(id, nome, horarios);
                if (sucesso) {
                    AppLogger.info(usuarioLogado.getEmail(), "Alterou ambiente ID: " + id);
                    System.out.println("Ambiente alterado com sucesso!");
                } else {
                    AppLogger.error(usuarioLogado.getEmail(), "Erro ao alterar ambiente ID: " + id);
                    System.out.println("Erro ao alterar ambiente. Tente novamente.");
                }
            } else if (opcao == 4) {
                System.out.print("Digite o ID do ambiente a ser deletado: ");
                int id = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                Ambiente ambienteParaDeletar = ambienteController.getAmbienteById(id);
                if (ambienteParaDeletar != null) {
                    System.out.println("Você deseja realmente deletar o ambiente com ID: " + id + ", Nome: " + ambienteParaDeletar.getNome() + "?");
                    System.out.println("1 - SIM");
                    System.out.println("2 - NÃO");
                    int confirmacao = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    if (confirmacao == 1) {
                        boolean sucesso = ambienteController.deletarAmbiente(id);
                        if (sucesso) {
                            AppLogger.info(usuarioLogado.getEmail(), "Deletou ambiente ID: " + id);
                            System.out.println("Ambiente deletado com sucesso!");
                        } else {
                            AppLogger.error(usuarioLogado.getEmail(), "Erro ao deletar ambiente ID: " + id);
                            System.out.println("Erro ao deletar ambiente. Tente novamente.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        AppLogger.info(usuarioLogado.getEmail(), "Cancelou a deleção do ambiente ID: " + id);
                    }
                } else {
                    System.out.println("Ambiente não encontrado.");
                    AppLogger.warn(usuarioLogado.getEmail(), "Tentativa de deletar ambiente não encontrado com ID: " + id);
                }
            } else if (opcao == 5) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void reservarAmbiente(Cliente cliente) {
        AmbienteController ambienteController = new AmbienteController();
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Digite o ID do ambiente que deseja reservar: ");
        int idAmbiente = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer
    
        Ambiente ambiente = ambienteController.getAmbienteById(idAmbiente);
        if (ambiente != null) {
            System.out.print("Digite o horário desejado (ex: 09:00-13:00): ");
            String horario = scanner.nextLine();
    
            boolean sucesso = cliente.reservarAmbiente(ambiente, horario);
            if (sucesso) {
                AppLogger.info(cliente.getEmail(), "Reservou ambiente ID: " + idAmbiente + " no horário: " + horario);
                System.out.println("Reserva realizada com sucesso!");
            } else {
                AppLogger.error(cliente.getEmail(), "Erro ao reservar ambiente ID: " + idAmbiente + " no horário: " + horario);
                System.out.println("HORÁRIO RESERVADO. ESCOLHA OUTRO HORÁRIO.");
            }
        } else {
            AppLogger.warn(cliente.getEmail(), "Tentativa de reservar ambiente não encontrado com ID: " + idAmbiente);
            System.out.println("Ambiente não encontrado.");
        }
    }

    private void listarMinhasReservas(Cliente cliente) {
        List<Reserva> reservas = cliente.getMinhasReservasFromDAO();
        if (reservas.isEmpty()) {
            System.out.println("Você não possui reservas cadastradas.");
        } else {
            System.out.println("=== Minhas Reservas ===");
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
        AppLogger.info(cliente.getEmail(), "Listou suas reservas.");
    }

    private void listarAmbientesDisponiveis() {
        AmbienteController ambienteController = new AmbienteController();

        List<Ambiente> ambientes = ambienteController.listarAmbientes();
        for (Ambiente ambiente : ambientes) {
            List<String> horariosDisponiveis = ambienteController.listarHorariosDisponiveis(ambiente.getId());
            if (!horariosDisponiveis.isEmpty()) {
                System.out.println("ID: " + ambiente.getId() + ", Nome: " + ambiente.getNome() + ", Horários disponíveis: " + horariosDisponiveis);
            }
        }
        AppLogger.info(usuarioLogado.getEmail(), "Listou ambientes disponíveis.");
    }

    private void gerirUsuarios(Scanner scanner, UsuarioController usuarioController) {
        while (true) {
            System.out.println("1- Cadastrar novo usuário");
            System.out.println("2- Listar usuários");
            System.out.println("3- Alterar usuário");
            System.out.println("4- Deletar usuário");
            System.out.println("5- Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (opcao == 1) {
                System.out.print("Digite o email do novo usuário: ");
                String novoEmail = scanner.nextLine();
                System.out.print("Digite a senha do novo usuário: ");
                String novaSenha = scanner.nextLine();
                System.out.print("Digite o tipo do novo usuário (Administrador/Cliente): ");
                String tipo = scanner.nextLine();

                boolean sucesso = usuarioController.cadastrarNovoUsuario(novoEmail, novaSenha, tipo);
                if (sucesso) {
                    AppLogger.info(usuarioLogado.getEmail(), "Cadastrou novo usuário: " + novoEmail);
                    System.out.println("Usuário cadastrado com sucesso!");
                } else {
                    AppLogger.error(usuarioLogado.getEmail(), "Erro ao cadastrar novo usuário: " + novoEmail);
                    System.out.println("Erro ao cadastrar usuário. Tente novamente.");
                }
            } else if (opcao == 2) {
                List<Usuario> usuarios = usuarioController.listarUsuarios();
                for (Usuario u : usuarios) {
                    String tipo = (u instanceof Administrador) ? "Administrador" : "Cliente";
                    System.out.println("ID " + u.getId() + ", Email: " + u.getEmail() + ", Tipo: " + tipo);
                }
                AppLogger.info(usuarioLogado.getEmail(), "Listou usuários.");
            } else if (opcao == 3) {
                System.out.print("Digite o ID do usuário a ser alterado: ");
                int id = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer
                System.out.print("Digite o novo email do usuário: ");
                String novoEmail = scanner.nextLine();
                System.out.print("Digite a nova senha do usuário: ");
                String novaSenha = scanner.nextLine();
                System.out.print("Digite o novo tipo do usuário (Administrador/Cliente): ");
                String tipo = scanner.nextLine();

                boolean sucesso = usuarioController.alterarUsuario(id, novoEmail, novaSenha, tipo);
                if (sucesso) {
                    AppLogger.info(usuarioLogado.getEmail(), "Alterou usuário ID: " + id);
                    System.out.println("Usuário alterado com sucesso!");
                } else {
                    AppLogger.error(usuarioLogado.getEmail(), "Erro ao alterar usuário ID: " + id);
                    System.out.println("Erro ao alterar usuário. Tente novamente.");
                }
            } else if (opcao == 4) {
                System.out.print("Digite o ID do usuário a ser deletado: ");
                int id = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                Usuario usuarioParaDeletar = usuarioController.getUsuarioById(id);
                if (usuarioParaDeletar != null) {
                    System.out.println("Você deseja realmente deletar o usuário com ID: " + id + ", Email: " + usuarioParaDeletar.getEmail() + "?");
                    System.out.println("1 - SIM");
                    System.out.println("2 - NÃO");
                    int confirmacao = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    if (confirmacao == 1) {
                        boolean sucesso = usuarioController.deletarUsuario(id);
                        if (sucesso) {
                            AppLogger.info(usuarioLogado.getEmail(), "Deletou usuário ID: " + id);
                            System.out.println("Usuário deletado com sucesso!");
                        } else {
                            AppLogger.error(usuarioLogado.getEmail(), "Erro ao deletar usuário ID: " + id);
                            System.out.println("Erro ao deletar usuário. Tente novamente.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        AppLogger.info(usuarioLogado.getEmail(), "Cancelou a deleção do usuário ID: " + id);
                    }
                } else {
                    System.out.println("Usuário não encontrado.");
                    AppLogger.warn(usuarioLogado.getEmail(), "Tentativa de deletar usuário não encontrado com ID: " + id);
                }
            } else if (opcao == 5) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
