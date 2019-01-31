package Business.Configur;

import java.util.List;
import DAOs.*;
import java.util.ArrayList;

public class Componente {
    private int idComponente;
    private float preco;
    private String nome;
    private int stock;
    private ComponenteDAO componenteDAO;
    private List<Integer> idObrigatoriedades;
    private List<Integer> idIncompatibilidades; 

    public Componente(int idComponente, float preco, String nome, int stock, List<Integer> idObrigatoriedades, List<Integer> idIncompatibilidades) {
        this.idComponente = idComponente;
        this.preco = preco;
        this.nome = nome;
        this.stock = stock;
        this.componenteDAO = new ComponenteDAO();
        this.idObrigatoriedades = idObrigatoriedades;
        this.idIncompatibilidades = idIncompatibilidades;
    }

    public Componente() {
        this.idComponente = 0;
        this.preco = (float) 0.0;
        this.nome = "";
        this.stock = 0;
        this.componenteDAO = new ComponenteDAO();
        this.idObrigatoriedades = new ArrayList<>();
        this.idIncompatibilidades = new ArrayList<>();
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ComponenteDAO getComponenteDAO() {
        return componenteDAO;
    }

    public void setComponenteDAO(ComponenteDAO comp) {
        this.componenteDAO = comp;
    }

    public List<Integer> getIdObrigatoriedades() {
        return idObrigatoriedades;
    }

    public void setIdObrigatoriedades(List<Integer> idObrigatoriedades) {
        this.idObrigatoriedades = idObrigatoriedades;
    }

    public List<Integer> getIdIncompatibilidades() {
        return idIncompatibilidades;
    }

    public void setIdIncompatibilidades(List<Integer> idIncompatibilidades) {
        this.idIncompatibilidades = idIncompatibilidades;
    }
    
    public List<Integer> verificaIncompatibilidadesComp(){
        List<Integer> incompatibilidades = getIdIncompatibilidades();
        return incompatibilidades;
    }
    
    public List<Integer> verificaSeObrigaTerOutrosComponentes(){
        List<Integer> obrigatoriedades = getIdObrigatoriedades();
        return obrigatoriedades;
    }
    
    public boolean podeAdicionar(List<Componente> verificador){
        if(verificador == null) return true;
        for(Integer i : idIncompatibilidades){
            for(Componente c : verificador){
                if(c.getIdComponente() == i) return false;
            }
        }
        return true;
    }
}
