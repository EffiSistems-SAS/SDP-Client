package Views;

import Controllers.EmpleadoController;
import Models.File;
import Models.HistorialCambios;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelCentralEmpleado extends JPanel {

    private final int Ancho = 570, Alto = 470;

    private EmpleadoController controller;

    private JScrollPane jsp;
    private JButton BtnBorrar, BtnHistorial, BtnDescargar;
    private JTable tablaArchivos, tablaCambios;

    private Color AzulOscuro = new Color(51, 80, 101);

    public PanelCentralEmpleado(EmpleadoController controller) {
        this.controller = controller;
        initTemplate();
    }

    public void initComponents() {
        tablaArchivos = new JTable();
        jsp = new JScrollPane();
        jsp.setVisible(false);
        BtnBorrar = new JButton("Borrar Archivo");
        BtnBorrar.setVisible(false);
        BtnHistorial = new JButton("Ver historial de versiones");
        BtnHistorial.setVisible(false);
        BtnDescargar = new JButton("Descargar archivo");
        BtnDescargar.setVisible(false);
    }

    public void initListeners() {
        BtnBorrar.addActionListener((e) -> {
            if (tablaArchivos.getSelectedRow() <= -1) {
                JOptionPane.showMessageDialog(null, "Ningún archivo seleccionado", "Status", JOptionPane.ERROR_MESSAGE);
            } else {
                String fileId = (String) tablaArchivos.getValueAt(tablaArchivos.getSelectedRow(), 1);
                System.out.println(fileId);
                int status = controller.eliminarArchivo(fileId);
                switch (status) {
                    case 200:
                        JOptionPane.showMessageDialog(null, "El archivo ha sido eliminado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                        verArchivos();
                        setArchivos();
                        break;
                    case 400:
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Status", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

        BtnHistorial.addActionListener((e) -> {
            if (tablaArchivos.getSelectedRow() <= -1) {
                JOptionPane.showMessageDialog(null, "Ningún archivo seleccionado", "Status", JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) tablaArchivos.getValueAt(tablaArchivos.getSelectedRow(), 4);
                HistorialCambios newHistorial = controller.verHistorialCambios(name);
                if (newHistorial == null) {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado no tiene modificaciones", "Status", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Founds changes", "Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        BtnDescargar.addActionListener((e) -> {
            repaint();
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
    }

    public void initTemplate() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setSize(new Dimension(Ancho, Alto));
        initComponents();
        initListeners();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void verArchivos() {

        reset();

        BtnBorrar.setSize(new Dimension(210, 40));
        BtnBorrar.setLocation(new Point((getWidth() - (BtnBorrar.getWidth() * 2) - 20) / 2, getHeight() - BtnBorrar.getHeight() - 5));
        BtnBorrar.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnBorrar.setBackground(AzulOscuro);
        BtnBorrar.setForeground(Color.WHITE);
        BtnBorrar.setFocusable(false);
        BtnBorrar.setVisible(true);
        add(BtnBorrar);

        BtnHistorial.setSize(new Dimension(210, 40));
        BtnHistorial.setLocation(new Point(BtnBorrar.getX() + BtnHistorial.getWidth() + 20, BtnBorrar.getY()));
        BtnHistorial.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnHistorial.setBackground(AzulOscuro);
        BtnHistorial.setForeground(Color.WHITE);
        BtnHistorial.setFocusable(false);
        BtnHistorial.setVisible(true);
        add(BtnHistorial);

        BtnDescargar.setSize(new Dimension(210, 40));
        BtnDescargar.setLocation(new Point((getWidth() - BtnDescargar.getWidth()) / 2, BtnBorrar.getY() - BtnDescargar.getHeight() - 5));
        BtnDescargar.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnDescargar.setBackground(AzulOscuro);
        BtnDescargar.setForeground(Color.WHITE);
        BtnDescargar.setFocusable(false);
        BtnDescargar.setVisible(true);
        add(BtnDescargar);

    }

    public void setArchivos() {
        File[] files = controller.obtenerArchivos();

        String[] cols = {"uploadDate", "id", "filename"};
        String[][] rows = new String[files.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length; j++) {
                switch (j) {
                    case 0:
                        rows[i][j] = files[i].getUploadDate();
                        break;
                    case 1:
                        rows[i][j] = files[i].getId();
                        break;
                    case 2:
                        rows[i][j] = files[i].getFilename();
                        break;
                }
            }
        }

        tablaArchivos = new JTable(rows, cols);
        tablaArchivos.setPreferredSize(new Dimension(1000, 1000));

        jsp = new JScrollPane(tablaArchivos);

        jsp.setSize(new Dimension(440, 355));
        jsp.setLocation(new Point((getWidth() - jsp.getWidth()) / 2, 15));
        jsp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jsp.setVisible(true);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp);
        jsp.updateUI();

        repaint();

    }

    public void reset() {
        jsp.setVisible(false);
        BtnBorrar.setVisible(false);
        BtnHistorial.setVisible(false);
        BtnDescargar.setVisible(false);
    }

}
