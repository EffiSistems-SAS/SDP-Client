package Views;

import javax.swing.JFrame;
import java.awt.Dimension;
//Import del controlador
import Controllers.AdministradorController;


/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class MenuAdministrador extends JFrame {
    
    //Graphic elements
    

    public AdministradorController AdministradorController;

    public MenuAdministrador() {
        AdministradorController = new AdministradorController();
    }
    
    private void initComponents(){
    
    }
    
    
    public void initTemplate(){
        setLayout(null);
        setSize(new Dimension(800,600));
        setTitle("Administrador SDP");
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }
}//end MenuAdministrador
