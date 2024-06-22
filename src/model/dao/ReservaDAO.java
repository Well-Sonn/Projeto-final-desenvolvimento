package model.dao;

import model.entity.Reserva;
import util.FileHandler;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static final String FILE_PATH = "data/reservas.json";
    private List<Reserva> reservas;

    public ReservaDAO() {
        reservas = FileHandler.readFromFile(FILE_PATH, new TypeToken<List<Reserva>>() {});
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
        FileHandler.writeToFile(FILE_PATH, reservas);
    }

    public List<Reserva> getAllReservas() {
        return reservas;
    }

    public void updateReserva(Reserva reserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId().equals(reserva.getId())) {
                reservas.set(i, reserva);
                break;
            }
        }
        FileHandler.writeToFile(FILE_PATH, reservas);
    }

    public void deleteReserva(String id) {
        reservas.removeIf(reserva -> reserva.getId().equals(id));
        FileHandler.writeToFile(FILE_PATH, reservas);
    }
}
