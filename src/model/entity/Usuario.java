package model.entity;

import model.abstracts.AbstractUsuario;

public class Usuario extends AbstractUsuario {
    public Usuario(String id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }
}
