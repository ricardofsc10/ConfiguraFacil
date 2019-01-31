package Business.Gestao;

import DAOs.FuncionarioDAO;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String password;
    private boolean fabrica;

    public Funcionario(int idFuncionario, String nome, String password, boolean fabrica) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.password = password;
        this.fabrica = fabrica;
    }
    
    public Funcionario() {
        this.idFuncionario = 0;
        this.nome = "";
        this.password = "";
        this.fabrica = false;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFabrica() {
        return fabrica;
    }

    public void setFabrica(boolean fabrica) {
        this.fabrica = fabrica;
    }
    
}
