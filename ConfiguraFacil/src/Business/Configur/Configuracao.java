package Business.Configur;

import Business.Configur.Cliente;
import java.util.List;
import DAOs.*;
import Interface.Func_CompIndisponivel;
import Interface.Func_CriarConfig;
import Interface.Login;
import java.util.ArrayList;



public class Configuracao {
    private int idConfiguracao;
    private boolean orcamento;
    private float orcamentoValor;
    private ComponenteDAO componentes;
    private PacoteDAO pacotes;
    private Cliente cliente;
    private boolean produzido;
    

    public Configuracao(int idConfiguracao, boolean orcamento, float orcamentoValor, Cliente cliente, boolean produzido) {
        this.idConfiguracao = idConfiguracao;
        this.orcamento = orcamento;
        this.orcamentoValor = orcamentoValor;
        this.componentes = new ComponenteDAO();
        this.pacotes = new PacoteDAO();
        this.cliente = cliente;
        this.produzido = produzido;
    }

    public Configuracao() {
        this.idConfiguracao = 0;
        this.orcamento = false;
        this.orcamentoValor = (float) 0.0;
        this.componentes = new ComponenteDAO();
        this.pacotes = new PacoteDAO();
        this.cliente = new Cliente();
        this.produzido = false;
    }
    
    public int getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(int idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public boolean isOrcamento() {
        return orcamento;
    }

    public void setOrcamento(boolean orcamento) {
        this.orcamento = orcamento;
    }

    public float getOrcamentoValor() {
        return orcamentoValor;
    }

    public void setOrcamentoValor(float orcamentoValor) {
        this.orcamentoValor = orcamentoValor;
    }

    public ComponenteDAO getComponentes() {
        return componentes;
    }

    public void setComponentes(ComponenteDAO componentes) {
        this.componentes = componentes;
    }

    public PacoteDAO getPacotes() {
        return pacotes;
    }

    public void setPacotes(PacoteDAO pacotes) {
        this.pacotes = pacotes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public boolean getProduzido() {
        return this.produzido;
    }

    public void setProduzido(boolean produzido) {
        this.produzido = produzido;
    }
    
}
