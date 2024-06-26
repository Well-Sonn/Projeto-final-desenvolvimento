package controller;

import java.util.List;
import model.dao.AmbienteDAO;
import model.entity.Ambiente;

public class AmbienteController {
    private AmbienteDAO ambienteDAO;

    public AmbienteController() {
        this.ambienteDAO = new AmbienteDAO();
    }

    public boolean cadastrarNovoAmbiente(String nome, String horarios) {
        Ambiente ambiente = new Ambiente(nome, horarios);
        return ambienteDAO.cadastrar(ambiente);
    }

    public List<Ambiente> listarAmbientes() {
        return ambienteDAO.listar();
    }

    public Ambiente getAmbienteById(int id) {
        return ambienteDAO.getById(id);
    }

    public boolean alterarAmbiente(int id, String nome, String horarios) {
        Ambiente ambiente = new Ambiente(nome, horarios);
        return ambienteDAO.alterar(ambiente);
    }

    public boolean deletarAmbiente(int id) {
        return ambienteDAO.deletar(id);
    }
}
