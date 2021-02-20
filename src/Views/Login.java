package Views;

import Controllers.LoginController;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
//
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Login extends JFrame {

    public MenuAdministrador m_MenuAdministrador;
    public MenuEmpleado m_MenuEmpleado;
    public LoginController LoginController;

    private int ALTO, ANCHO;
    private JLabel txtName, txtPassword, imgLogo;
    private JTextField inputName;
    private JButton btnLogin;
    private JPasswordField inputPassword;
    private static Login login;
    
    private Color Gris = new Color(226, 231, 236);
    private Color AzulOscuro = new Color(0, 37, 63);

    private Login() {
        LoginController = new LoginController();
        
        ALTO = 500;
        ANCHO = 500;
        setSize(ANCHO, ALTO);
        initComponents();
        initListeners();
        
        
    }

    public static Login getLogin() {
        if (login == null) {
            login = new Login();
        }
        return login;
    }

    private void initComponents() {
        imgLogo = new JLabel();
        imgLogo.setSize(250, 250);
        imgLogo.setLocation((getWidth() - imgLogo.getWidth()) / 2, 5);
        setImagen(imgLogo, "logo.png");

        
        txtName = new JLabel("Correo:");
        txtName.setSize(150, 30);
        txtName.setLocation(115, 300);
        txtName.setFont(new Font("Arial", Font.BOLD, 15));
        add(txtName);

        inputName = new JTextField();
        inputName.setSize(200, 30);
        inputName.setLocation(215, 300);
        inputName.setBackground(Gris);
        inputName.setText("miguel35697@hotmail.com");
        add(inputName);

        txtPassword = new JLabel("Contraseña:");
        txtPassword.setSize(150, 30);
        txtPassword.setLocation(115, 340);
        txtPassword.setFont(new Font("Arial", Font.BOLD, 15));
        add(txtPassword);
        
        inputPassword = new JPasswordField();
        inputPassword.setSize(200, 30);
        inputPassword.setLocation(215, 340);
        inputPassword.setBackground(Gris);
        inputPassword.setText("contra123");
        add(inputPassword);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setSize(150, 30);
        btnLogin.setLocation((getWidth() - btnLogin.getWidth()) / 2, 420);
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogin.setFocusable(false);
        btnLogin.setBackground(AzulOscuro);
        btnLogin.setForeground(Color.WHITE);
        add(btnLogin);

    }

    private void initListeners() {
        btnLogin.addActionListener((event) -> {
            int res = LoginController.iniciarSesion(inputName.getText(),String.valueOf(inputPassword.getPassword()));
            switch (res) {
                case 202: {
                    MenuAdministrador menu = new MenuAdministrador();
                    menu.initTemplate();
                    dispose();
                    reset();
                    break;
                }
                case 200: {
                    MenuEmpleado menu = new MenuEmpleado(inputName.getText());
                    menu.initTemplate();
                    dispose();
                    reset();
                    break;
                }
                case 503: {
                    JOptionPane.showMessageDialog(null, "Server not running", "Status", JOptionPane.WARNING_MESSAGE);
                    reset();
                    break;
                }
                case 404: {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrecta.", "Error", JOptionPane.WARNING_MESSAGE);
                    reset();
                    break;
                }
            }
        });
    }

    public void initTemplate() {
        setTitle("Iniciar Sesión");
        setLayout(null);

        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void reset() {
        inputName.setText(null);
        inputPassword.setText(null);
    }

    private void setImagen(JLabel label, String nombreImg) {
        String rutaBase = "src/Resources/" + nombreImg;
        ImageIcon instr = new ImageIcon(rutaBase);
        Image imginstr = instr.getImage();
        Image nuevaimagen = imginstr.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(nuevaimagen);
        label.setIcon(imagen);
        add(label);
    }
}//end Login
