package model.dao;

import model.abstracts.AbstractUsuario;
import model.entity.Administrador;
import model.entity.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO {
    private List<AbstractUsuario> usuarios;
    private static final String FILE_NAME = "usuarios.json";

    public UsuarioDAO() {
        this.usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public void create(AbstractUsuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public AbstractUsuario read(String email, String senha) {
        for (AbstractUsuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void update(AbstractUsuario usuario) {
        // Implementar lógica de atualização, se necessário
    }

    public void delete(String email) {
        usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
        salvarUsuarios();
    }

    public List<AbstractUsuario> listAll() {
        return usuarios;
    }

    private void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (AbstractUsuario usuario : usuarios) {
                writer.write(usuario instanceof Administrador ? "Administrador" : "Cliente");
                writer.write(",");
                writer.write(usuario.getNome());
                writer.write(",");
                writer.write(usuario.getEmail());
                writer.write(",");
                writer.write(usuario.getSenha());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarUsuarios() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length != 4) {
                    continue;  // Ignora linhas malformadas
                }
                String tipo = partes[0];
                String nome = partes[1];
                String email = partes[2];
                String senha = partes[3];

                AbstractUsuario usuario;
                if ("Administrador".equals(tipo)) {
                    usuario = new Administrador(nome, email, senha);
                } else if ("Cliente".equals(tipo)) {
                    usuario = new Cliente(nome, email, senha);
                } else {
                    continue;  // Ignora tipos de usuário desconhecidos
                }
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}