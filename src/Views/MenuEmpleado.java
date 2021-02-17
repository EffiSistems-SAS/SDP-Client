package Views;

import Controllers.EmpleadoController;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class MenuEmpleado extends JFrame {

    public EmpleadoController m_EmpleadoController;

    public MenuEmpleado() {

    }
    
    private void initComponents(){
    }
    
     public void initTemplate(){
        setLayout(null);
        setSize(new Dimension(800,600));
        setTitle("Empleado sdp");
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }
}//end MenuEmpleado
