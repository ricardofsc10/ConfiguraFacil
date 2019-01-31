package DAOs;

import Business.Configur.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponenteDAO {
    public List<Componente> getComponentes(int idConfig) throws SQLException, Exception{
        Connection c = Connect.connect();
        List<Componente> res = new ArrayList<>();
        if(c!=null) { 
            PreparedStatement ps1 = c.prepareStatement("SELECT * FROM Configuracao_Componente cc, Componente c WHERE cc.idConfiguracao = ? AND cc.idComponente = c.idComponente");
            ps1.setInt(1, idConfig);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()) { 
                Componente comp = new Componente(); 
                comp.setIdComponente(rs1.getInt("idComponente")); 
                comp.setPreco(rs1.getFloat("preco")); 
                comp.setNome(rs1.getString("nome")); 
                comp.setStock(rs1.getInt("stock"));                
                res.add(comp);
            }
            PreparedStatement ps2 = c.prepareStatement("SELECT * FROM Configuracao_Componente cc, Componente c, Obrigatoriedade o WHERE cc.idConfiguracao = ? AND cc.idComponente = c.idComponente AND cc.idComponente = o.idComponente");
            ps2.setInt(1, idConfig);
            ResultSet rs2 = ps2.executeQuery();
            List<Integer> obrigatoriedades = new ArrayList<>();
            while(rs2.next()){
                int i = rs2.getInt("idComponente");
                Componente comp = getComponente(i);
                obrigatoriedades.add(rs2.getInt("idComponenteObrigatorio"));
                comp.setIdObrigatoriedades(obrigatoriedades);
            }
            PreparedStatement ps3 = c.prepareStatement("SELECT * FROM Configuracao_Componente cc, Componente c, Incompatibilidade i WHERE cc.idConfiguracao = ? AND cc.idComponente = c.idComponente AND cc.idComponente = i.idComponente");
            ps3.setInt(1, idConfig);
            ResultSet rs3 = ps3.executeQuery();
            List<Integer> incompatibilidades = new ArrayList<>();
            while(rs3.next()){
                int i = rs3.getInt("idComponente");
                Componente comp = getComponente(i);
                incompatibilidades.add(rs3.getInt("idComponenteIncompativel"));
                comp.setIdIncompatibilidades(incompatibilidades);
            }
            Connect.close(c);
        }
        else{
            throw new Exception("Unable to establish connection");
        }
        return res;
    }
    
    public Componente getComponente(int idComponente) throws SQLException, Exception{
        Connection c = Connect.connect();
        Componente comp = new Componente();
        if(c!=null) { 
            PreparedStatement ps1 = c.prepareStatement("SELECT * FROM Componente WHERE idComponente = ?");
            ps1.setInt(1, idComponente);
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()) { 
                comp.setIdComponente(rs1.getInt("idComponente")); 
                comp.setPreco(rs1.getFloat("preco"));
                comp.setNome(rs1.getString("nome")); 
                comp.setStock(rs1.getInt("stock"));
            }
            PreparedStatement ps2 = c.prepareStatement("SELECT * FROM Componente c, Obrigatoriedade o WHERE c.idComponente = ? AND c.idComponente = o.idComponente");
            ps2.setInt(1, idComponente);
            ResultSet rs2 = ps2.executeQuery();
            List<Integer> obrigatoriedades = new ArrayList<>();
            while(rs2.next()){
                int i = rs2.getInt("idComponente");
                obrigatoriedades.add(rs2.getInt("idComponenteObrigatorio"));
                comp.setIdObrigatoriedades(obrigatoriedades);
            }
            PreparedStatement ps3 = c.prepareStatement("SELECT * FROM Componente c, Incompatibilidade i WHERE c.idComponente = ? AND c.idComponente = i.idComponente");
            ps3.setInt(1, idComponente);
            ResultSet rs3 = ps3.executeQuery();
            List<Integer> incompatibilidades = new ArrayList<>();
            while(rs3.next()){
                int i = rs3.getInt("idComponente");
                incompatibilidades.add(rs3.getInt("idComponenteIncompativel"));
                comp.setIdIncompatibilidades(incompatibilidades);
            }
            Connect.close(c);
        }
        else{
            throw new Exception("Unable to establish connection");
        }
        return comp;
    }

    
    public void addComponente(int idComponente, int idConfiguracao) throws SQLException, Exception{
        Connection c = Connect.connect();
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("INSERT INTO Configuracao_Componente VALUES (?,?);");
            ps.setInt(1, idConfiguracao);
            ps.setInt(2, idComponente);
            ps.executeUpdate();         
            Connect.close(c);
            }
        else{
            throw new Exception("Unable to establish connection");
        }
    }
    
    public void removeComponente(int idComponente) throws SQLException, Exception{
        Connection c = Connect.connect();
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("DELETE FROM Configuracao_Componente WHERE idComponente = ?");
            ps.setInt(1, idComponente);
            ps.executeUpdate();         
            Connect.close(c);
            }
        else{
            throw new Exception("Unable to establish connection");
        }
    }
    
    public List<Componente> getComponentes() throws SQLException, Exception{
        Connection c = Connect.connect();
        List<Componente> res = new ArrayList<>();
        if(c!=null) { 
            PreparedStatement ps1 = c.prepareStatement("SELECT * FROM Componente");
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()) { 
                Componente comp = new Componente(); 
                comp.setIdComponente(rs1.getInt("idComponente")); 
                comp.setPreco(rs1.getFloat("preco")); 
                comp.setNome(rs1.getString("nome")); 
                comp.setStock(rs1.getInt("stock"));                
                res.add(comp);
            }
            Connect.close(c);
        }
            else{
                throw new Exception("Componente não encontrado.");
            }
        return res;
    } 
    
    public List<Integer> getComponentesPacote(int idPacote) throws SQLException, Exception{
         Connection c = Connect.connect();
        List<Integer> res = new ArrayList<>();
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("SELECT pc.idComponente FROM Pacote_Componente pc WHERE pc.idPacote = ?");
            ps.setInt(1, idPacote);
            ResultSet rs1 = ps.executeQuery();
            while(rs1.next()) { 
                res.add(rs1.getInt("idComponente")); 
            }
        }
            else{
                throw new Exception("Pacote não encontrado.");
            }
        return res;
    }
    public void atualizaStock(int idComponente,int quant) throws SQLException, Exception{
        Connection c = Connect.connect();
        if(c!=null) {
            PreparedStatement ps = c.prepareStatement("UPDATE Componente SET stock = (stock + ?) WHERE idComponente = ?");
            ps.setInt(1,quant);
            ps.setInt(2,idComponente);
            ps.executeUpdate();         
            Connect.close(c);
        }
        else{
            throw new Exception("Componente não encontrado.");
        }
    }
}


/* FALTA AS OBRIGATORIEDADES E AS INCOMPATIBILIDADES
    public Componente getComponente(int idComponente) throws SQLException, Exception{
        Connection c = Connect.connect();
        Componente res;
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Componente WHERE idComponente = ?");
            ps.setInt(1, idComponente);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                res = new Componente(); 
                res.setIdComponente(rs.getInt("idComponente")); 
                res.setPreco(rs.getFloat("preco"));
                res.setNome(rs.getString("nome")); 
                res.setStock(rs.getInt("stock"));                 
                Connect.close(c);
                return res;
            }
            else{
                throw new Exception("Componente não encontrado.");
            }
        }
        else{
            throw new Exception("Unable to establish connection");
        }
    }
*/