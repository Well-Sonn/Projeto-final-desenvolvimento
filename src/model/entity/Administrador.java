package model.entity;

import model.abstracts.AbstractUsuario;

public class Administrador extends AbstractUsuario {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Administrador: " + getNome() + ", Email: " + getEmail());
    }
}
