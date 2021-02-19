package Views;

import Controllers.AdministradorController;
import Models.Empleado;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelCentral extends JPanel {

    private final int Ancho = 570, Alto = 470;

    private JLabel LblNombre, LblPass, LblId, LblCorreo, LblRol, LblCargo, LblInfoEmpleado;
    private JTextField TxtFldNombre, TxtFldId, TxtFldCorreo, TxtFldRol, TxtFldCargo;
    private JPasswordField TxtFldPass;
    private JButton BtnAction, BtnObtener;
    private JTable tabla;
    private JScrollPane jsp;
    
    private AdministradorController controller = new AdministradorController();

    private Color AzulClaro = new Color(123, 195, 229);

    public PanelCentral() {
        initTemplate();
    }

    private void initComponents() {
        LblNombre = new JLabel("Nombre");
        LblNombre.setVisible(false);
        LblPass = new JLabel("Contraseña");
        LblPass.setVisible(false);
        LblId = new JLabel("Id");
        LblId.setVisible(false);
        LblCorreo = new JLabel("Correo");
        LblCorreo.setVisible(false);
        LblCargo = new JLabel("Cargo");
        LblCargo.setVisible(false);
        LblRol = new JLabel("Rol");
        LblRol.setVisible(false);
        LblInfoEmpleado = new JLabel();
        LblInfoEmpleado.setVisible(false);

        TxtFldNombre = new JTextField("Nombre");
        TxtFldNombre.setVisible(false);
        TxtFldPass = new JPasswordField();
        TxtFldPass.setVisible(false);
        TxtFldId = new JTextField("Id");
        TxtFldId.setVisible(false);
        TxtFldCorreo = new JTextField("Correo");
        TxtFldCorreo.setVisible(false);
        TxtFldCargo = new JTextField("Cargo");
        TxtFldCargo.setVisible(false);
        TxtFldRol = new JTextField("Rol");
        TxtFldRol.setVisible(false);

        BtnAction = new JButton();
        BtnAction.setVisible(false);

        BtnObtener = new JButton();
        BtnObtener.setVisible(false);
        
        jsp = new JScrollPane();
        jsp.setVisible(false);

        tabla = new JTable();
        tabla.setVisible(false);
    }

    private void initListeners() {

        BtnAction.addActionListener((e) -> {
            switch (BtnAction.getActionCommand()) {
                case "Crear usuario":
                    if (verifNewUser()) {
                        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de ingresar a este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (res == 0) {
                            int status = controller.crearUsuario(TxtFldNombre.getText(), TxtFldCorreo.getText(), convert(TxtFldPass.getPassword()), TxtFldCargo.getText(), TxtFldRol.getText());
                            switch (status) {
                                case 200:
                                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                                    reset();
                                    break;
                                case 400:
                                    JOptionPane.showMessageDialog(null, "Usuario creado anteriormente", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Unexpected error", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene los campos", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "Eliminar usuario":
                    if (!TxtFldCorreo.getText().isBlank()) {
                        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == 0) {
                            int status = controller.eliminarUsuario(TxtFldCorreo.getText());
                            switch (status) {
                                case 202:
                                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                                    reset();
                                    break;
                                case 400:
                                    JOptionPane.showMessageDialog(null, "Usuario creado anteriormente", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Unexpected error", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene el campo", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "Editar usuario":
                    if (verifNewUser()) {
                        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de editar los datos de este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == 0) {
                            int status = controller.editarUsuario(LblId.getText(), TxtFldNombre.getText(), TxtFldCorreo.getText(), String.valueOf(TxtFldPass.getPassword()), TxtFldCargo.getText(), TxtFldRol.getText());
                            switch (status) {
                                case 200:
                                    JOptionPane.showMessageDialog(null, "Usuario editado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
                                    reset();
                                    break;
                                case 400:
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Unexpected error", "Status", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene los campos", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "Buscar usuario":
                    if (!TxtFldCorreo.getText().isBlank()) {
                        Empleado empleado = controller.consultarEmpleado(TxtFldCorreo.getText());
                        if (empleado == null) {
                            JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            String info = "<html><head><meta charset='UTF-8'></head><body><p style='text-align: center'>Nombre: " + empleado.getNombre()
                                    + "</p><br><p style='text-align: center'>Id: " + empleado.getId()
                                    + "</p><br><p style='text-align: center'>Correo: " + empleado.getCorreo()
                                    + "</p><br><p style='text-align: center'>Contraseña: " + empleado.getContrasena()
                                    + "</p><br><p style='text-align: center'>Rol: " + empleado.getRol()
                                    + "</p><br><p style='text-align: center'>Cargo: " + empleado.getCargo() + "</p></body></html>";
                            LblInfoEmpleado.setText(info);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene el campo", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                default:
                    System.out.println("¿Na-Nani?");
            }
        });

        BtnObtener.addActionListener((event) -> {
            Empleado empleado = controller.consultarEmpleado(TxtFldCorreo.getText());
            System.out.println(empleado);
            if (empleado != null) {
                TxtFldNombre.setText(empleado.getNombre());
                TxtFldCargo.setText(empleado.getCargo());
                TxtFldRol.setText(empleado.getRol());
                TxtFldPass.setText(empleado.getContrasena());
                LblId.setText("Id: " + empleado.getId());

            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
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
    }

    public void insertarEmpleado() {
        reset();

        LblNombre.setText("Nombre");
        LblNombre.setSize(new Dimension(125, 30));
        LblNombre.setLocation(new Point((getWidth() - LblNombre.getWidth() - 300) / 2, 25));
        LblNombre.setHorizontalAlignment(JLabel.CENTER);
        LblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        LblNombre.setVisible(true);
        add(LblNombre);

        TxtFldNombre.setSize(300, 30);
        TxtFldNombre.setLocation(LblNombre.getX() + LblNombre.getWidth() + 5, 25);
        TxtFldNombre.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldNombre.setHorizontalAlignment(JTextField.CENTER);
        TxtFldNombre.setVisible(true);
        add(TxtFldNombre);

        LblPass.setText("Contraseña");
        LblPass.setSize(new Dimension(125, 30));
        LblPass.setLocation(new Point((getWidth() - LblPass.getWidth() - 300) / 2, 90));
        LblPass.setHorizontalAlignment(JLabel.CENTER);
        LblPass.setFont(new Font("Arial", Font.BOLD, 20));
        LblPass.setVisible(true);
        add(LblPass);

        TxtFldPass.setSize(300, 30);
        TxtFldPass.setLocation(LblPass.getX() + LblPass.getWidth() + 5, 90);
        TxtFldPass.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldPass.setHorizontalAlignment(JTextField.CENTER);
        TxtFldPass.setVisible(true);
        add(TxtFldPass);

        LblCorreo.setText("Correo");
        LblCorreo.setSize(new Dimension(125, 30));
        LblCorreo.setLocation(new Point((getWidth() - LblCorreo.getWidth() - 300) / 2, 215));
        LblCorreo.setHorizontalAlignment(JLabel.CENTER);
        LblCorreo.setFont(new Font("Arial", Font.BOLD, 20));
        LblCorreo.setVisible(true);
        add(LblCorreo);

        TxtFldCorreo.setSize(300, 30);
        TxtFldCorreo.setLocation(LblCorreo.getX() + LblCorreo.getWidth() + 5, 215);
        TxtFldCorreo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCorreo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCorreo.setVisible(true);
        add(TxtFldCorreo);

        LblRol.setText("Rol");
        LblRol = new JLabel("Rol");
        LblRol.setSize(new Dimension(125, 30));
        LblRol.setLocation(new Point((getWidth() - LblRol.getWidth() - 300) / 2, 280));
        LblRol.setHorizontalAlignment(JLabel.CENTER);
        LblRol.setFont(new Font("Arial", Font.BOLD, 20));
        LblRol.setVisible(true);
        add(LblRol);

        TxtFldRol.setSize(300, 30);
        TxtFldRol.setLocation(LblRol.getX() + LblRol.getWidth() + 5, 280);
        TxtFldRol.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldRol.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldRol.setHorizontalAlignment(JTextField.CENTER);
        TxtFldRol.setVisible(true);
        add(TxtFldRol);

        LblCargo.setText("Cargo");
        LblCargo.setSize(new Dimension(125, 30));
        LblCargo.setLocation(new Point((getWidth() - LblCargo.getWidth() - 300) / 2, 345));
        LblCargo.setHorizontalAlignment(JLabel.CENTER);
        LblCargo.setFont(new Font("Arial", Font.BOLD, 20));
        LblCargo.setVisible(true);
        add(LblCargo);

        TxtFldCargo.setSize(300, 30);
        TxtFldCargo.setLocation(LblCargo.getX() + LblCargo.getWidth() + 5, 345);
        TxtFldCargo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCargo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCargo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCargo.setVisible(true);
        add(TxtFldCargo);

        BtnAction.setText("Crear usuario");
        BtnAction.setSize(new Dimension(150, 40));
        BtnAction.setLocation(new Point((getWidth() - BtnAction.getWidth()) / 2, 410));
        BtnAction.setFont(new Font("Arial", Font.BOLD, 15));
        BtnAction.setBackground(AzulClaro);
        BtnAction.setFocusable(false);
        BtnAction.setVisible(true);
        add(BtnAction);

        repaint();
    }

    public void eliminarEmpleado() {
        reset();

        LblCorreo.setText("Correo");
        LblCorreo.setSize(new Dimension(125, 30));
        LblCorreo.setLocation(new Point((getWidth() - LblCorreo.getWidth() - 300) / 2, (getHeight() / 2) - (LblCorreo.getHeight() / 2)));
        LblCorreo.setHorizontalAlignment(JLabel.CENTER);
        LblCorreo.setFont(new Font("Arial", Font.BOLD, 20));
        LblCorreo.setVisible(true);
        add(LblCorreo);

        TxtFldCorreo.setSize(300, 30);
        TxtFldCorreo.setLocation(LblCorreo.getX() + LblCorreo.getWidth() + 5, LblCorreo.getY());
        TxtFldCorreo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCorreo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCorreo.setVisible(true);
        add(TxtFldCorreo);

        BtnAction.setText("Eliminar usuario");
        BtnAction.setSize(new Dimension(150, 40));
        BtnAction.setLocation(new Point((getWidth() - BtnAction.getWidth()) / 2, 410));
        BtnAction.setFont(new Font("Arial", Font.BOLD, 15));
        BtnAction.setBackground(AzulClaro);
        BtnAction.setFocusable(false);
        BtnAction.setVisible(true);
        add(BtnAction);

        repaint();
    }

    public void editarEmpleado() {
        reset();

        LblCorreo.setText("Correo del usuario");
        LblCorreo.setSize(new Dimension(190, 30));
        LblCorreo.setLocation(new Point((getWidth() - LblCorreo.getWidth() - 300) / 2, 25));
        LblCorreo.setHorizontalAlignment(JLabel.CENTER);
        LblCorreo.setFont(new Font("Arial", Font.BOLD, 15));
        LblCorreo.setVisible(true);
        add(LblCorreo);

        TxtFldCorreo.setSize(300, 30);
        TxtFldCorreo.setLocation(LblCorreo.getX() + LblCorreo.getWidth() + 5, 25);
        TxtFldCorreo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCorreo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCorreo.setVisible(true);
        add(TxtFldCorreo);

        LblNombre.setText("Nuevo nombre");
        LblNombre.setSize(new Dimension(150, 30));
        LblNombre.setLocation(new Point((getWidth() - LblNombre.getWidth() - 300) / 2, 155));
        LblNombre.setHorizontalAlignment(JLabel.CENTER);
        LblNombre.setFont(new Font("Arial", Font.BOLD, 15));
        LblNombre.setVisible(true);
        add(LblNombre);

        TxtFldNombre.setSize(300, 30);
        TxtFldNombre.setLocation(LblNombre.getX() + LblNombre.getWidth() + 5, 155);
        TxtFldNombre.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldNombre.setHorizontalAlignment(JTextField.CENTER);
        TxtFldNombre.setVisible(true);
        add(TxtFldNombre);

        LblPass.setText("Nueva contraseña");
        LblPass.setSize(new Dimension(150, 30));
        LblPass.setLocation(new Point((getWidth() - LblPass.getWidth() - 300) / 2, 215));
        LblPass.setHorizontalAlignment(JLabel.CENTER);
        LblPass.setFont(new Font("Arial", Font.BOLD, 15));
        LblPass.setVisible(true);
        add(LblPass);

        TxtFldPass.setSize(300, 30);
        TxtFldPass.setLocation(LblPass.getX() + LblPass.getWidth() + 5, 215);
        TxtFldPass.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldPass.setHorizontalAlignment(JTextField.CENTER);
        TxtFldPass.setVisible(true);
        add(TxtFldPass);

        LblRol.setText("Nuevo rol");
        LblRol.setSize(new Dimension(150, 30));
        LblRol.setLocation(new Point((getWidth() - LblRol.getWidth() - 300) / 2, 280));
        LblRol.setHorizontalAlignment(JLabel.CENTER);
        LblRol.setFont(new Font("Arial", Font.BOLD, 15));
        LblRol.setVisible(true);
        add(LblRol);

        TxtFldRol.setSize(300, 30);
        TxtFldRol.setLocation(LblRol.getX() + LblRol.getWidth() + 5, 280);
        TxtFldRol.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldRol.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldRol.setHorizontalAlignment(JTextField.CENTER);
        TxtFldRol.setVisible(true);
        add(TxtFldRol);

        LblCargo.setText("Nuevo cargo");
        LblCargo.setSize(new Dimension(150, 30));
        LblCargo.setLocation(new Point((getWidth() - LblCargo.getWidth() - 300) / 2, 345));
        LblCargo.setHorizontalAlignment(JLabel.CENTER);
        LblCargo.setFont(new Font("Arial", Font.BOLD, 15));
        LblCargo.setVisible(true);
        add(LblCargo);

        TxtFldCargo.setSize(300, 30);
        TxtFldCargo.setLocation(LblCargo.getX() + LblCargo.getWidth() + 5, 345);
        TxtFldCargo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCargo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCargo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCargo.setVisible(true);
        add(TxtFldCargo);

        BtnAction.setText("Editar usuario");
        BtnAction.setSize(new Dimension(150, 40));
        BtnAction.setLocation(new Point((getWidth() - BtnAction.getWidth()) / 2, 410));
        BtnAction.setFont(new Font("Arial", Font.BOLD, 15));
        BtnAction.setBackground(AzulClaro);
        BtnAction.setFocusable(false);
        BtnAction.setVisible(true);
        add(BtnAction);

        BtnObtener.setText("Obtener datos");
        BtnObtener.setSize(new Dimension(150, 40));
        BtnObtener.setLocation(new Point(((getWidth() - BtnObtener.getWidth()) / 2) + 100, 85));
        BtnObtener.setFont(new Font("Arial", Font.BOLD, 15));
        BtnObtener.setBackground(AzulClaro);
        BtnObtener.setFocusable(false);
        BtnObtener.setVisible(true);
        add(BtnObtener);

        LblId.setSize(new Dimension(250, 30));
        LblId.setLocation(new Point(((getWidth() - LblId.getWidth()) / 2) - 150, 85));
        LblId.setHorizontalAlignment(JLabel.CENTER);
        LblId.setFont(new Font("Arial", Font.BOLD, 15));
        LblId.setVisible(true);
        add(LblId);

        repaint();
    }

    public void listarEmpleados() {
        reset();
        Empleado[] listado = controller.getListadoUsuarios();
        String[] cols = {"Id", "Nombre", "Contraseña", "Correo", "Rol", "Cargo"};
        String[][] rows = new String[listado.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length; j++) {
                switch (j) {
                    case 0:
                        rows[i][j] = listado[i].getId();
                        break;
                    case 1:
                        rows[i][j] = listado[i].getNombre();
                        break;
                    case 2:
                        rows[i][j] = listado[i].getContrasena();
                        break;
                    case 3:
                        rows[i][j] = listado[i].getCorreo();
                        break;
                    case 4:
                        rows[i][j] = listado[i].getRol();
                        break;
                    case 5:
                        rows[i][j] = listado[i].getCargo();
                        break;
                }
            }
        }

        tabla = new JTable(rows,cols);
        tabla.setPreferredSize(new Dimension(1000,10000));
        tabla.setLocation(0, 0);
        
        jsp = new JScrollPane(tabla);
        
        jsp.setSize(new Dimension(Ancho, Alto-5));
        jsp.setLocation(0, 0);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp);
        jsp.updateUI();

    }

    public void buscarEmpleado() {
        reset();

        LblCorreo.setText("Correo");
        LblCorreo.setSize(new Dimension(125, 30));
        LblCorreo.setLocation(new Point((getWidth() - LblCorreo.getWidth() - 300) / 2, 5));
        LblCorreo.setHorizontalAlignment(JLabel.CENTER);
        LblCorreo.setFont(new Font("Arial", Font.BOLD, 20));
        LblCorreo.setVisible(true);
        add(LblCorreo);

        TxtFldCorreo.setSize(300, 30);
        TxtFldCorreo.setLocation(LblCorreo.getX() + LblCorreo.getWidth() + 5, LblCorreo.getY());
        TxtFldCorreo.setFont(new Font("Arial", Font.BOLD, 15));
        TxtFldCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TxtFldCorreo.setHorizontalAlignment(JTextField.CENTER);
        TxtFldCorreo.setVisible(true);
        add(TxtFldCorreo);

        LblInfoEmpleado.setSize(new Dimension(getWidth() - 10, getHeight() - 105));
        LblInfoEmpleado.setLocation(5, 40);
        LblInfoEmpleado.setFont(new Font("Arial", Font.BOLD, 15));
        LblInfoEmpleado.setHorizontalAlignment(JLabel.CENTER);
        LblInfoEmpleado.setVisible(true);
        add(LblInfoEmpleado);

        BtnAction.setText("Buscar usuario");
        BtnAction.setSize(new Dimension(150, 40));
        BtnAction.setLocation(new Point((getWidth() - BtnAction.getWidth()) / 2, 410));
        BtnAction.setFont(new Font("Arial", Font.BOLD, 15));
        BtnAction.setBackground(AzulClaro);
        BtnAction.setFocusable(false);
        BtnAction.setVisible(true);
        add(BtnAction);

        repaint();
    }

    public void reset() {
        LblNombre.setVisible(false);
        LblPass.setVisible(false);
        LblId.setVisible(false);
        LblCorreo.setVisible(false);
        LblCargo.setVisible(false);
        LblRol.setVisible(false);
        LblInfoEmpleado.setVisible(false);

        TxtFldNombre.setText(null);
        TxtFldNombre.setVisible(false);
        TxtFldPass.setText(null);
        TxtFldPass.setVisible(false);
        TxtFldId.setText(null);
        TxtFldId.setVisible(false);
        TxtFldCorreo.setText(null);
        TxtFldCorreo.setVisible(false);
        TxtFldCargo.setText(null);
        TxtFldCargo.setVisible(false);
        TxtFldRol.setText(null);
        TxtFldRol.setVisible(false);

        BtnAction.setText(null);
        BtnAction.setVisible(false);

        BtnObtener.setVisible(false);
        
        jsp.setVisible(false);
        tabla.setVisible(false);
    }

    public boolean verifNewUser() {
        if (!(TxtFldNombre.getText().isBlank())
                && !(TxtFldPass.getText().isBlank())
                && !(TxtFldCorreo.getText().isBlank())
                && !(TxtFldCargo.getText().isBlank())
                && !(TxtFldRol.getText().isBlank())) {
            return true;
        } else {
            return false;
        }
    }

    private String convert(char[] password) {
        String retorno = "";
        for (int i = 0; i < password.length; i++) {
            retorno += password[i];
        }
        return retorno;
    }

}
