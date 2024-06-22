package model.abstracts;

public abstract class AbstractUsuario {
    protected String id;
    protected String nome;
    protected String email;
    protected String senha;

    public AbstractUsuario(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

            // Getters
        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }

        // Setters
        public void setId(String id) {
            this.id = id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

}
