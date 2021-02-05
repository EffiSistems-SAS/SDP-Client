package Views;

import Controllers.LoginController;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
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
    public LoginController m_LoginController;

    private final int ALTO, ANCHO;
    private final JLabel txtName, txtPassword, imgLogo;
    private final JTextField inputName;
    private final JButton btnLogin;
    private final JPasswordField inputPassword;

    public Login() {
        ALTO = 500;
        ANCHO = 500;
        txtName = new JLabel("Nombre de usuario:");
        txtPassword = new JLabel("Contraseña:");
        imgLogo = new JLabel();
        btnLogin = new JButton("Iniciar sesión");
        inputName = new JTextField();
        inputPassword = new JPasswordField();
    }

    private void setImagen(JLabel label, String nombreImg) {
        String rutaBase = "src/resources/" + nombreImg;
        ImageIcon instr = new ImageIcon(rutaBase);
        Image imginstr = instr.getImage();
        Image nuevaimagen = imginstr.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(nuevaimagen);
        label.setIcon(imagen);
        add(label);
    }

    private void initComponents() {
        imgLogo.setSize(250, 250);
        imgLogo.setLocation((getWidth() - imgLogo.getWidth()) / 2, 5);
        //imgLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setImagen(imgLogo, "logo.png");

        txtName.setSize(150, 30);
        txtName.setLocation(60, 300);
        txtName.setFont(new Font("Arial", Font.BOLD, 15));
        add(txtName);

        inputName.setSize(200, 30);
        inputName.setLocation(215, 300);
        inputName.setBackground(new Color(226, 231, 236));
        add(inputName);

        txtPassword.setSize(150, 30);
        txtPassword.setLocation(115, 340);
        txtPassword.setFont(new Font("Arial", Font.BOLD, 15));
        add(txtPassword);

        inputPassword.setSize(200, 30);
        inputPassword.setLocation(215, 340);
        inputPassword.setBackground(new Color(226, 231, 236));
        add(inputPassword);

        btnLogin.setSize(150, 30);
        btnLogin.setLocation((getWidth() - btnLogin.getWidth()) / 2, 420);
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogin.setFocusable(false);
        btnLogin.setBackground(new Color(0, 37, 63));
        btnLogin.setForeground(Color.WHITE);
        add(btnLogin);

    }

    private void initListeners() {
        btnLogin.addActionListener((event) -> {
        });
    }

    public void initTemplate() {
        setTitle("Login");
        setLayout(null);
        setSize(ANCHO, ALTO);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        initComponents();
        initListeners();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}//end Login
