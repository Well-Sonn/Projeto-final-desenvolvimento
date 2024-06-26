package model.entity;

public class Reserva {
    private int id;
    private int idCliente;
    private int idAmbiente;
    private String horario;

    public Reserva(int id, int idCliente, int idAmbiente, String horario) {
        this.id = id;
        this.idCliente = idCliente;
        this.idAmbiente = idAmbiente;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return String.format("Reserva [ID: %d, Cliente ID: %d, Ambiente ID: %d, Horário: %s]",
                             id, idCliente, idAmbiente, horario);
    }

}
