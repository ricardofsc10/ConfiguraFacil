package DAOs;

import Business.Gestao.Funcionario;
import java.sql.*;

public class FuncionarioDAO {
    
    public Funcionario getFuncionario(String nomeFunc) throws SQLException, Exception{
        Connection c = Connect.connect();
        Funcionario res;
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Funcionario WHERE nome = ?");
            ps.setString(1, nomeFunc);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                res = new Funcionario(); 
                res.setIdFuncionario(rs.getInt("idFuncionario")); 
                res.setNome(rs.getString("nome")); 
                res.setPassword(rs.getString("password")); 
                res.setFabrica(rs.getBoolean("fabrica"));                
                Connect.close(c);
                return res;
            }
            else{
                throw new Exception("Funcionário não encontrado.");
            }
        }
        else{
            throw new Exception("Unable to establish connection");
        }
    } 
}
