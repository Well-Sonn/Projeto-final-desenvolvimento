package model.entity;

import java.util.List;

public class Ambiente {
    private String id;
    private String nome;
    private List<String> horarios;
    private boolean ativo;

    public Ambiente(String id, String nome, List<String> horarios, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.horarios = horarios;
        this.ativo = ativo;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
