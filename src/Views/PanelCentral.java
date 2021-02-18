package Views;

import Controllers.AdministradorController;
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
import javax.swing.JTextField;

public class PanelCentral extends JPanel {

    private final int Ancho = 570, Alto = 470;

    private JLabel LblNombre, LblPass, LblId, LblCorreo, LblRol, LblCargo;
    private JTextField TxtFldNombre, TxtFldId, TxtFldCorreo, TxtFldRol, TxtFldCargo;
    private JPasswordField TxtFldPass;
    private JButton BtnAction;
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
                                case 200:
                                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Status", JOptionPane.INFORMATION_MESSAGE);
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

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene los campos", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "Buscar usuario":
                    if (!TxtFldCorreo.getText().isBlank()) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Rellene el campo", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                default:
                    System.out.println("¿Na-Nani?");
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

        repaint();
    }

    public void listarEmpleados() {

    }

    public void buscarEmpleado() {
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
