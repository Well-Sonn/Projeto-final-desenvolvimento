package controller;

import model.abstracts.AbstractUsuario;
import model.dao.UsuarioDAO;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void cadastrarUsuario(AbstractUsuario usuario) {
        usuarioDAO.create(usuario);
    }

    public AbstractUsuario login(String email, String senha) {
        return usuarioDAO.read(email, senha);
    }

    // Métodos adicionais para alterar, deletar e listar usuários
}