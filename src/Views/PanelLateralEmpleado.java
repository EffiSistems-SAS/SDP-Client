package Views;

import Controllers.EmpleadoController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelLateralEmpleado extends JPanel {

    private final int Ancho = 200, Alto = 515;
    private JButton BtnSubir, BtnArchivos;
    private PanelCentralEmpleado RefCentral;
    private Color AzulOscuro = new Color(51, 80, 101);
    private final ArrayList<String> extensiones = new ArrayList<String>(Arrays.asList("shp", "shx", "dbf", "sbn", "sbx", "fbn", "fbx", "ain", "aih", "atx", "ixs", "mxs", "prj", "xml", "cpg", "mxd"));

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

        BtnArchivos = new JButton("Ver archivos");
        BtnArchivos.setSize(new Dimension(190, 40));
        BtnArchivos.setLocation(new Point(5, getHeight() - BtnArchivos.getHeight() - 5));
        BtnArchivos.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnArchivos.setBackground(AzulOscuro);
        BtnArchivos.setForeground(Color.WHITE);
        BtnArchivos.setFocusable(false);
        add(BtnArchivos);

    }

    private void initListeners() {
        BtnSubir.addActionListener((e) -> {
            RefCentral.reset();
            repaint();
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            fc.setAcceptAllFileFilterUsed(false);
            File files = fc.getSelectedFile();

            if (!(files == null)) {

                String name = files.getName();
                int pos = name.indexOf(".");
                String ext = name.substring(pos + 1);

                if (extensiones.contains(ext)) {
                    int res = controller.subirArchivo(files);
                    switch (res) {
                        case 200:
                            JOptionPane.showMessageDialog(null, "Archivo subido exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 202:
                            JOptionPane.showMessageDialog(null, "Registro actualizado", "Status", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 400:
                            JOptionPane.showMessageDialog(null, "Hubo un error", "Status", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Extensión invalida", "Status", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Archivo no seleccionado", "Status", JOptionPane.ERROR_MESSAGE);
            }
        });

        BtnArchivos.addActionListener((e) -> {
            RefCentral.verArchivos();
            RefCentral.setArchivos();
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
