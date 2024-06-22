package controller;

import model.dao.UsuarioDAO;
import model.entity.Usuario;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.getAllUsuarios();
    }

    public void addUsuario(Usuario usuario) {
        List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
        usuarios.add(usuario);
        usuarioDAO.saveAllUsuarios(usuarios);
    }

    public void updateUsuario(Usuario usuario) {
        List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        usuarioDAO.saveAllUsuarios(usuarios);
    }

    public void deleteUsuario(String id) {
        List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
        usuarios.removeIf(u -> u.getId().equals(id));
        usuarioDAO.saveAllUsuarios(usuarios);
    }
}
