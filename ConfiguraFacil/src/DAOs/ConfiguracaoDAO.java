package DAOs;

import Business.Configur.Cliente;
import Business.Configur.Configuracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiguracaoDAO {
    public Configuracao getConfiguracao(int idConfiguracao) throws SQLException, Exception{
        Connection c = Connect.connect();
        Configuracao res;
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Configuracao WHERE idConfiguracao = ?");
            ps.setInt(1, idConfiguracao);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                res = new Configuracao(); 
                res.setIdConfiguracao(rs.getInt("idConfiguracao")); 
                res.setOrcamento(rs.getBoolean("orcamento"));
                res.setOrcamentoValor(rs.getFloat("orcamentoValor"));
                Cliente cliente = res.getCliente();
                cliente.setNome(rs.getString("nome"));
                cliente.seteMail(rs.getString("email"));
                cliente.setMorada(rs.getString("morada"));
                cliente.setPagamento(rs.getFloat("pagamento"));
                cliente.setNif(rs.getString("nif"));
                res.setCliente(cliente);
                res.setProduzido(rs.getBoolean("produzido"));
                Connect.close(c);
                return res;
            }
            else{
                throw new Exception("Configuracao não encontrada.");
            }
        }
        else{
            throw new Exception("Unable to establish connection");
        }
    }
    
        public void addConfiguracao(int idConfiguracao, boolean orcamento, float orcamentoValor, int idFuncionario, int produzido) throws SQLException, Exception{
        Connection c = Connect.connect();
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("INSERT INTO Configuracao VALUES (?,?,?,?,?,?,?,?,?,?);");
            ps.setInt(1, idConfiguracao);
            ps.setBoolean(2, orcamento);
            ps.setFloat(3, orcamentoValor);
            ps.setInt(4, idFuncionario);
            ps.setString(5, "Ronaldo");
            ps.setString(6, "Ronaldo@gmail.com");
            ps.setString(7, "Ronaldo Street");
            ps.setFloat(8, (float) 0.0);
            ps.setInt(9,produzido);
            ps.executeUpdate();         
            Connect.close(c);
            }
        else{
            throw new Exception("Unable to establish connection");
        }
    }
}
