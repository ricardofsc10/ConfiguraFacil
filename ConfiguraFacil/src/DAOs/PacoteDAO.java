package DAOs;

import Business.Configur.Pacote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacoteDAO {
    public Pacote getPacote(int idPacote) throws SQLException, Exception{
        Connection c = Connect.connect();
        Pacote p = new Pacote();
        if(c!=null) { 
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Pacote p WHERE p.idPacote = ?");
            ps.setInt(1, idPacote);
            ResultSet rs1 = ps.executeQuery();
            if(rs1.next()) { 
                p.setIdPacote(rs1.getInt("idPacote")); 
                p.setPreco(rs1.getFloat("preco"));
                p.setNome(rs1.getString("nome")); 
            }
            Connect.close(c);
        }
        else{
            throw new Exception("Unable to establish connection");
        }
        return p;
    }
}
