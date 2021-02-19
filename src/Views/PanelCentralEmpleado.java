package Views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelCentralEmpleado extends JPanel {
    
    private final int Ancho = 570, Alto = 470;
    
    public void initComponents(){
        
    }
    
    public void initListeners(){
    
    }
    
    public void initTemplate() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setSize(new Dimension(Ancho, Alto));
        initComponents();
        initListeners();
    }
    
}
