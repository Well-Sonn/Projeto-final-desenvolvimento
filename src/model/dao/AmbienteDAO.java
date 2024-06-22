package model.dao;

import model.entity.Ambiente;
import util.FileHandler;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AmbienteDAO {
    private static final String FILE_PATH = "data/ambientes.json";
    private List<Ambiente> ambientes;

    public AmbienteDAO() {
        ambientes = FileHandler.readFromFile(FILE_PATH, new TypeToken<List<Ambiente>>() {});
        if (ambientes == null) {
            ambientes = new ArrayList<>();
        }
    }

    public void addAmbiente(Ambiente ambiente) {
        ambientes.add(ambiente);
        FileHandler.writeToFile(FILE_PATH, ambientes);
    }

    public List<Ambiente> getAllAmbientes() {
        return ambientes;
    }

    public void updateAmbiente(Ambiente ambiente) {
        for (int i = 0; i < ambientes.size(); i++) {
            if (ambientes.get(i).getId().equals(ambiente.getId())) {
                ambientes.set(i, ambiente);
                break;
            }
        }
        FileHandler.writeToFile(FILE_PATH, ambientes);
    }

    public void deleteAmbiente(String id) {
        ambientes.removeIf(ambiente -> ambiente.getId().equals(id));
        FileHandler.writeToFile(FILE_PATH, ambientes);
    }
}
