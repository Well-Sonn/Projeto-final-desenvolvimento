package controller;

import java.util.List;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import model.entity.Ambiente;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private AmbienteController ambienteController;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.ambienteController = new AmbienteController();
    }

    public Usuario login(String email, String senha) {
        return usuarioDAO.getUsuarioByEmailAndPassword(email, senha);
    }

    public boolean cadastrarNovoUsuario(String email, String senha, String tipo) {
        return usuarioDAO.cadastrarNovoUsuario(email, senha, tipo);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public boolean alterarUsuario(int id, String email, String senha, String tipo) {
        return usuarioDAO.alterarUsuario(id, email, senha, tipo);
    }

    public boolean deletarUsuario(int id) {
        return usuarioDAO.deletarUsuario(id);
    }

    public Usuario getUsuarioById(int id) {
        return usuarioDAO.getUsuarioById(id);
    }

    public List<Ambiente> listarAmbientesDisponiveis() {
        return ambienteController.listarAmbientes();
    }

    public List<String> listarHorariosDisponiveis(int idAmbiente) {
        return ambienteController.listarHorariosDisponiveis(idAmbiente);
    }
}
