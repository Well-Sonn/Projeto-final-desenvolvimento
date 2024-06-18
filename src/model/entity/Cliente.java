package model.entity;
import java.util.Objects;

import model.abstracts.AbstractUsuario;

public class Cliente extends AbstractUsuario {
    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Cliente: " + getNome() + ", Email: " + getEmail());
    }
}