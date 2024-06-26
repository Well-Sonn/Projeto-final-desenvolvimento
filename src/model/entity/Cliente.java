package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.dao.ReservaDAO;

public class Cliente extends Usuario {
    private List<Reserva> minhasReservas;

    public Cliente(int id, String email, String senha) {
        super(id, email, senha);
        this.minhasReservas = new ArrayList<>();
    }

    public boolean reservarAmbiente(Ambiente ambiente, String horario) {
        ReservaDAO reservaDAO = new ReservaDAO();
        boolean sucesso = reservaDAO.criar(this.getId(), ambiente.getId(), horario);
        if (sucesso) {
            Reserva reserva = new Reserva(this.getId(), ambiente.getId(), id, horario);
            return minhasReservas.add(reserva);
        }
        return false;
    }

    public List<Reserva> getMinhasReservasFromDAO() {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.listarPorCliente(this.getId());
    }
}
