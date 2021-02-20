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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelLateralEmpleado extends JPanel {

    private final int Ancho = 200, Alto = 515;
    private JButton BtnSubir, BtnBajar, BtnHistorial, BtnEliminar;
    private PanelCentralEmpleado RefCentral;
    private Color AzulOscuro = new Color(51, 80, 101);

    private EmpleadoController controller;

    public PanelLateralEmpleado(PanelCentralEmpleado ref, EmpleadoController controller) {
        RefCentral = ref;
        this.controller = controller;
        initTemplate();
    }

    public void initComponents() {
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
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            fc.setAcceptAllFileFilterUsed(false);
            File files = fc.getSelectedFile();
            if (!(files == null)) {
                int res = controller.subirArchivo(files);
                switch (res) {
                    case 200:
                        JOptionPane.showMessageDialog(null, "Archivo subido exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo no seleccionado", "Status", JOptionPane.ERROR_MESSAGE);
            }
        });

        BtnBajar.addActionListener((e) -> {
            int res = controller.bajarArchivo("XD.pdf");
            switch (res) {
                case 200:
                    JOptionPane.showMessageDialog(null, "Archivo descargado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 400:
                    JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Status", JOptionPane.ERROR_MESSAGE);
                    break;
                case 401:
                    JOptionPane.showMessageDialog(null, "Got IOException", "Status", JOptionPane.ERROR_MESSAGE);
                    break;
                case 402:
                    JOptionPane.showMessageDialog(null, "El directorio no se ha podido crear", "Status", JOptionPane.ERROR_MESSAGE);
                    break;
                case 403:
                    JOptionPane.showMessageDialog(null, "No se ha podido descargar el archivo", "Status", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

        BtnHistorial.addActionListener((e) -> {
            //RefCentral.editarEmpleado();
        });

        BtnEliminar.addActionListener((e) -> {
            //RefCentral.listarEmpleados();
        });

    }

    public void initTemplate() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(Ancho, Alto));
        initComponents();
        initListeners();

    }

}
