package controller;

import java.util.ArrayList;
import java.util.List;
import model.dao.AmbienteDAO;
import model.entity.Ambiente;

public class AmbienteController {
    private AmbienteDAO ambienteDAO;

    public AmbienteController() {
        this.ambienteDAO = new AmbienteDAO();
    }

    public boolean cadastrarNovoAmbiente(String nome, String horarios) {
        String horariosConcatenados = String.join(";", horarios);
        Ambiente ambiente = new Ambiente(nome, horariosConcatenados);
        return ambienteDAO.cadastrarAmbiente(ambiente);
    }

    public List<Ambiente> listarAmbientes() {
        return ambienteDAO.listar();
    }

    public Ambiente getAmbienteById(int id) {
        return ambienteDAO.getById(id);
    }

    public boolean alterarAmbiente(int id, String novoNome, String novosHorarios) {
        Ambiente ambienteAlterado = new Ambiente(novoNome, novosHorarios);
        ambienteAlterado.setId(id);
        return ambienteDAO.alterar(ambienteAlterado);
    }

    public boolean deletarAmbiente(int id) {
        return ambienteDAO.deletar(id);
    }

    public List<String> listarHorariosDisponiveis(int idAmbiente) {
        List<String> horariosDisponiveis = new ArrayList<>();
        Ambiente ambiente = ambienteDAO.getById(idAmbiente);

        if (ambiente != null) {
            String[] horarios = ambiente.getHorarios().split(";");
            for (String horario : horarios) {
                horariosDisponiveis.add(horario.trim());
            }
        }

        return horariosDisponiveis;
    }

    
}
