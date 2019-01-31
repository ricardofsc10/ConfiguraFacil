import Business.*;
import Interface.*;

public class Main {
    
    public static void main(String[] args){
        ConfiguraFacil cf = new ConfiguraFacil();
        
        new Login(cf).setVisible(true);
    }
}
