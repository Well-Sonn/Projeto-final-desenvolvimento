package model.dao;

import model.entity.Usuario;
import com.google.gson.reflect.TypeToken;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String USUARIOS_FILE_PATH = "data/usuarios.json";
    private List<Usuario> usuarios;

    public UsuarioDAO() {
        usuarios = new ArrayList<>();
    }

    // Método para carregar os usuários do arquivo JSON
    public void carregarUsuariosFromJson() {
        TypeToken<List<Usuario>> typeToken = new TypeToken<List<Usuario>>() {};
        usuarios = FileHandler.readFromFile(USUARIOS_FILE_PATH, typeToken);
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
    }

    // Método para salvar todos os usuários no arquivo JSON
    public void saveAllUsuarios(List<Usuario> usuarios) {
        FileHandler.writeToFile(USUARIOS_FILE_PATH, usuarios);
    }

    // Métodos para manipulação dos usuários
    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        saveAllUsuarios(usuarios);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public void updateUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        saveAllUsuarios(usuarios);
    }

    public void deleteUsuario(String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
        saveAllUsuarios(usuarios);
    }
}
