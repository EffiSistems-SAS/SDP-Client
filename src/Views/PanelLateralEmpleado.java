package Views;

import Controllers.EmpleadoController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class PanelLateralEmpleado extends JPanel {
    
    private final int Ancho = 200, Alto = 515;
    private JButton BtnSubir, BtnBajar, BtnHistorial, BtnEliminar;
    private PanelCentralEmpleado RefCentral;
    private Color AzulOscuro = new Color(51, 80, 101);
    
    public EmpleadoController controller;
    
    
    public PanelLateralEmpleado(PanelCentralEmpleado ref,EmpleadoController controller){
        RefCentral = ref;
        this.controller = controller;
        initTemplate();
    }
    
    public void initComponents(){
        BtnSubir = new JButton("Subir Archivo");
        BtnSubir.setSize(new Dimension(190, 40));
        BtnSubir.setLocation(new Point(5, 5));
        BtnSubir.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnSubir.setBackground(AzulOscuro);
        BtnSubir.setForeground(Color.WHITE);
        BtnSubir.setFocusable(false);
        add(BtnSubir);

        BtnBajar = new JButton("Descargar Archivo");
        BtnBajar.setSize(new Dimension(190, 40));
        BtnBajar.setLocation(new Point(5, 120));
        BtnBajar.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnBajar.setBackground(AzulOscuro);
        BtnBajar.setForeground(Color.WHITE);
        BtnBajar.setFocusable(false);
        add(BtnBajar);

        BtnHistorial = new JButton("Historial de Cambios");
        BtnHistorial.setSize(new Dimension(190, 40));
        BtnHistorial.setLocation(new Point(5, 235));
        BtnHistorial.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnHistorial.setBackground(AzulOscuro);
        BtnHistorial.setForeground(Color.WHITE);
        BtnHistorial.setFocusable(false);
        add(BtnHistorial);

        BtnEliminar = new JButton("Eliminar archivo");
        BtnEliminar.setSize(new Dimension(190, 40));
        BtnEliminar.setLocation(new Point(5, 350));
        BtnEliminar.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnEliminar.setBackground(AzulOscuro);
        BtnEliminar.setForeground(Color.WHITE);
        BtnEliminar.setFocusable(false);
        add(BtnEliminar);
        
    }
    
    private void initListeners() {
        BtnSubir.addActionListener((e) -> {
            //RefCentral.insertarEmpleado();
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            fc.setAcceptAllFileFilterUsed(false);
            File files = fc.getSelectedFile();
            
            int res = controller.subirArchivo(files);
            
            
        });

        BtnBajar.addActionListener((e) -> {
            //RefCentral.eliminarEmpleado();
        });

        BtnHistorial.addActionListener((e) -> {
            //RefCentral.editarEmpleado();
        });

        BtnEliminar.addActionListener((e) -> {
            //RefCentral.listarEmpleados();
        });


    }
    
    public void initTemplate(){
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(Ancho, Alto));
        initComponents();
        initListeners();
    
    }
    
}
