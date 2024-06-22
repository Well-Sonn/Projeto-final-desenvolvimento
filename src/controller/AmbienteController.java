package controller;

import model.dao.AmbienteDAO;
import model.entity.Ambiente;

import java.util.List;

public class AmbienteController {
    private AmbienteDAO ambienteDAO;

    public AmbienteController() {
        this.ambienteDAO = new AmbienteDAO();
    }

    public void addAmbiente(Ambiente ambiente) {
        ambienteDAO.addAmbiente(ambiente);
    }

    public List<Ambiente> getAllAmbientes() {
        return ambienteDAO.getAllAmbientes();
    }

    public void updateAmbiente(Ambiente ambiente) {
        ambienteDAO.updateAmbiente(ambiente);
    }

    public void deleteAmbiente(String id) {
        ambienteDAO.deleteAmbiente(id);
    }
}
