/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Configur;

import Business.Configur.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joao
 */
public class ConfigAtual {
    private boolean orcamento;
    private float orcamentoValor;
    private List<Integer> componentes;
    private List<Integer> pacotes;
    private Cliente cliente;
    private boolean produzido;

    public ConfigAtual(boolean orcamento, float orcamentoValor, List<Integer> componentes, List<Integer> pacotes, Cliente cliente, boolean produzido) {
        this.orcamento = orcamento;
        this.orcamentoValor = orcamentoValor;
        this.componentes = componentes;
        this.pacotes = pacotes;
        this.cliente = cliente;
        this.produzido = produzido;
    }

    public ConfigAtual() {
        this.orcamento = false;
        this.orcamentoValor = 0;
        this.componentes = new ArrayList<>();
        this.pacotes = new ArrayList<>();
        this.cliente = new Cliente();
        this.produzido = false;
    }
    
    

    public boolean isOrcamento() {
        return orcamento;
    }

    public float getOrcamentoValor() {
        return orcamentoValor;
    }

    public List<Integer> getComponentes() {
        return componentes;
    }

    public List<Integer> getPacotes() {
        return pacotes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isProduzido() {
        return produzido;
    }

    public void setOrcamento(boolean orcamento) {
        this.orcamento = orcamento;
    }

    public void setOrcamentoValor(float orcamentoValor) {
        this.orcamentoValor = orcamentoValor;
    }

    public void setComponentes(List<Integer> componentes) {
        this.componentes = componentes;
    }

    public void setPacotes(List<Integer> pacotes) {
        this.pacotes = pacotes;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProduzido(boolean produzido) {
        this.produzido = produzido;
    }
    
    public void retiraIncompatibilidades(List<Integer> incompatibilidades){
        for(Integer i : incompatibilidades){
            if(componentes.contains(i)) componentes.remove(i);
        }
    }
    
    public void adicionaObrigatoriedades(List<Integer> obrigatoriedades){
        for(Integer i : obrigatoriedades){
            if(!componentes.contains(i)) componentes.add(i);
        }
    }
    
    public void adicionaComps(Componente comp, List<Integer> obrigatoriedades) throws Exception{
        componentes.add(comp.getIdComponente());
        
        for(Integer i : obrigatoriedades){
            if(!componentes.contains(i)) componentes.add(i);
        }
    }
    
}
