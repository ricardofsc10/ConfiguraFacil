package Business.Configur;

import java.util.List;
import DAOs.*;
import java.util.ArrayList;

public class Pacote {
    private int idPacote;
    private float preco;
    private String nome;
    private ComponenteDAO componentes;
    private List<Integer> idComponentes;

    public Pacote(int idPacote, float preco, String nome, List<Integer> idComponentes) {
        this.idPacote = idPacote;
        this.preco = preco;
        this.nome = nome;
        this.idComponentes = idComponentes;
    }

    public Pacote() {
        this.idPacote = 0;
        this.preco = 0;
        this.nome = "";
        this.idComponentes = new ArrayList<>();
    }
    
    public int getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(int idPacote) {
        this.idPacote = idPacote;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public List<Integer> getComponentes() throws Exception {
        return componentes.getComponentesPacote(this.idPacote);
    }

    public void setComponentes(ComponenteDAO componentes) {
        this.componentes = componentes;
    }

    public List<Integer> getIdComponentes() {
        return idComponentes;
    }

    public void setIdComponentes(List<Integer> idComponentes) {
        this.idComponentes = idComponentes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
