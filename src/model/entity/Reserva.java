package model.entity;

public class Reserva {
    private String id;
    private String usuarioId;
    private String ambienteId;
    private String horario;

    public Reserva(String id, String usuarioId, String ambienteId, String horario) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.ambienteId = ambienteId;
        this.horario = horario;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getAmbienteId() {
        return ambienteId;
    }

    public String getHorario() {
        return horario;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setAmbienteId(String ambienteId) {
        this.ambienteId = ambienteId;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
