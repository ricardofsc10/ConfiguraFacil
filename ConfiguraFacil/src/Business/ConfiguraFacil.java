package Business;

import Business.Configur.Componente;
import Business.Configur.ConfigAtual;
import Business.Configur.Pacote;
import Business.Gestao.Funcionario;
import DAOs.*;
import Interface.Func_CriarConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfiguraFacil {
    private final ConfiguracaoDAO configuracoes;
    private final FuncionarioDAO funcionarios;
    private final PacoteDAO pacotes;
    private final ComponenteDAO componentes;
    private ConfigAtual configAtual;

    public ConfiguraFacil() {
        this.funcionarios = new FuncionarioDAO();
        this.configuracoes = new ConfiguracaoDAO();
        this.pacotes = new PacoteDAO();
        this.componentes = new ComponenteDAO();
        this.configAtual = new ConfigAtual();
    }
    
    public ConfigAtual getConfigAtual(){
        return this.configAtual;
    }
    
    public void setConfigAtual(ConfigAtual cf){
        this.configAtual = cf;
    }
    
    public int adicionarComponente(int idComponente, Func_CriarConfig fcc) throws Exception{
        Componente comp = componentes.getComponente(idComponente);
        List<Integer> incomp = new ArrayList<>();
        List<Integer> obrig = new ArrayList<>();
        
        if(comp.getStock() == 0)
            return 1;
        
        incomp = comp.verificaIncompatibilidadesComp();
        
        boolean retiraincomps = false;
        if(!incomp.isEmpty()) retiraincomps = fcc.informaIncompatibilidades(incomp);
        if(retiraincomps == false) return 2;
        else configAtual.retiraIncompatibilidades(incomp);
        
        obrig = comp.verificaSeObrigaTerOutrosComponentes();
        
        boolean adicionaobrig = false;
        if(!obrig.isEmpty()) adicionaobrig = fcc.informaObrigatoriedades(obrig);
        if(adicionaobrig == false) return 3;
        else configAtual.adicionaComps(comp, obrig);
        
        return 0;
    }

    public int escolherPacote(int idPacote, Func_CriarConfig fcc) throws Exception {
	Pacote p = pacotes.getPacote(idPacote);
        List<Integer> componentesPacote = p.getComponentes();
        
        List<Integer> ComponentesConfigAtual = this.configAtual.getComponentes();
        
        List<Integer> incompatibilidades = new ArrayList<>();
        List<Integer> obrigatoriedades = new ArrayList<>();
        
        for(Integer id : componentesPacote){
            Componente c = componentes.getComponente(id);
            int stock = c.getStock();
            if(stock == 0) return 1;
            
            List<Integer> incompatibilidadesComp = c.getIdIncompatibilidades();
            for(Integer ci : incompatibilidadesComp)
                if(ComponentesConfigAtual.contains(ci)) incompatibilidades.add(ci);
            
            List<Integer> obrigatoriedadesComp = c.getIdObrigatoriedades();
            for(Integer co : obrigatoriedadesComp)
                if(!ComponentesConfigAtual.contains(co)) obrigatoriedades.add(co);
        }
        
        incompatibilidades = removereps(incompatibilidades);
        boolean retiraincomps = false;
        if(!incompatibilidades.isEmpty()) retiraincomps = fcc.informaIncompatibilidades(incompatibilidades);
        if(retiraincomps == false) return 2;
        else configAtual.retiraIncompatibilidades(incompatibilidades);
        
        obrigatoriedades = removereps(obrigatoriedades);
        boolean adicionaobrig = false;
        if(!obrigatoriedades.isEmpty()) adicionaobrig = fcc.informaObrigatoriedades(obrigatoriedades);
        if(adicionaobrig == false) return 3;
        else configAtual.adicionaObrigatoriedades(obrigatoriedades);
        
        return 0;
        
    }
    
    public List<Integer> removereps(List<Integer> lista){
        int tamanho = lista.size();
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i<tamanho ; i++){
            int j = i+1;
            for(; j<tamanho ; j++){
                if(lista.get(i) == lista.get(j)) break;
            }
            if(j == tamanho) res.add(lista.get(i));
        }
        return res;
    }

   public void receberFornecimento(int idComponente, int quant) throws Exception {
	Componente comp = componentes.getComponente(idComponente);

	if (comp == null)  return ;

	componentes.atualizaStock(idComponente,quant);
}


    public List<Componente> configuracaoOtima(float orcamento) throws SQLException, Exception {
        
        List<Componente> res = new ArrayList<>();
        int id = 0;
        float valorOrcamento = orcamento;
        float minimo = (float) 2000000000;
        
        while(valorOrcamento >= 50) {
            for(Componente c : componentes.getComponentes()){
                if(c.getPreco() < minimo){
                    minimo = c.getPreco();
                    id=c.getIdComponente();
                }
            }
            Componente c = componentes.getComponente(id);
            if(c.podeAdicionar(res)){
                valorOrcamento -= minimo;
                componentes.atualizaStock(id,(-1));
                res.add(c);
                componentes.addComponente(id,1);
            }
            List<Integer> obrigatoriedades = c.getIdObrigatoriedades();
                for(Integer i : obrigatoriedades){
                    Componente c1 = componentes.getComponente(i);
                    valorOrcamento -= c1.getPreco();
                    componentes.atualizaStock(id,(-1));
                    res.add(c1);
                    componentes.addComponente(i, 1);
            }
        }       
        configuracoes.addConfiguracao(1,true,orcamento,1,1);
        return res;
    }

    public int login(String username, char[] pass) throws SQLException, Exception{
	Funcionario f =  funcionarios.getFuncionario(username);
        
        if(f==null) return 0;
        
        String passCorreta = f.getPassword();
        
        String npass = new String(pass);
        
        if( passCorreta.equals(npass) == true){
            if(f.isFabrica()) return 1;
            else return 2;
        }
        else return 3;
    }
}
