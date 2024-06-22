package controller;

import model.dao.ReservaDAO;
import model.entity.Reserva;

import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public void addReserva(Reserva reserva) {
        reservaDAO.addReserva(reserva);
    }

    public List<Reserva> getAllReservas() {
        return reservaDAO.getAllReservas();
    }

    public void updateReserva(Reserva reserva) {
        reservaDAO.updateReserva(reserva);
    }

    public void deleteReserva(String id) {
        reservaDAO.deleteReserva(id);
    }
}
