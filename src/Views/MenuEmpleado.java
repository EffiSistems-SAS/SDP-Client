package Views;

import Controllers.EmpleadoController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuEmpleado extends JFrame {

    private EmpleadoController m_EmpleadoController;
    private final int Ancho = 800, Alto = 600;
    private PanelLateralEmpleado PnlLateral;
    private PanelCentralEmpleado PnlCentral;
    private JLabel LblTitle, LblWelcome;
    private JButton BtnQuit;

    private Color AzulClaro = new Color(123, 195, 229);
    private Color Verde = new Color(31, 134, 101);

    public MenuEmpleado(String correo) {
        m_EmpleadoController = new EmpleadoController(correo);
    }

    private void initComponents() {
        LblTitle = new JLabel("SECRETARÍA DISTRITAL DE PLANEACIÓN");
        LblTitle.setSize(new Dimension(getWidth() - 25, 30));
        LblTitle.setLocation(new Point(5, 5));
        LblTitle.setOpaque(true);
        LblTitle.setBackground(AzulClaro);
        LblTitle.setHorizontalAlignment(JLabel.CENTER);
        LblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        LblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(LblTitle);

        PnlCentral = new PanelCentralEmpleado(m_EmpleadoController);
        PnlCentral.setLocation(new Point(210, 40));
        add(PnlCentral);

        PnlLateral = new PanelLateralEmpleado(PnlCentral, m_EmpleadoController);
        PnlLateral.setLocation(new Point(5, 40));
        add(PnlLateral);

        BtnQuit = new JButton();
        BtnQuit.setSize(new Dimension(50, 40));
        BtnQuit.setLocation(new Point(getWidth() - BtnQuit.getWidth() - 20, getHeight() - BtnQuit.getHeight() - 45));
        BtnQuit.setFont(new Font("Arial", Font.BOLD, 15));
        setImagen(BtnQuit, "logout.png");
        BtnQuit.setBackground(Verde);
        BtnQuit.setFocusable(false);
        add(BtnQuit);

        LblTitle = new JLabel("BIENVENIDO");
        LblTitle.setSize(new Dimension(515, 40));
        LblTitle.setLocation(new Point(210, 515));
        LblTitle.setOpaque(true);
        LblTitle.setBackground(AzulClaro);
        LblTitle.setHorizontalAlignment(JLabel.CENTER);
        LblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        LblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(LblTitle);
    }

    private void initListeners() {
        BtnQuit.addActionListener((e) -> {
            dispose();
            Login newLogin = Login.getLogin();
            newLogin.initTemplate();
        });
    }

    public void initTemplate() {
        setLayout(null);
        setSize(new Dimension(Ancho, Alto));
        setTitle("Empleado SDP");
        Image icon = new ImageIcon(getClass().getResource("/Resources/logomin.png")).getImage();
        setIconImage(icon);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        initListeners();
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        setResizable(false);
    }

    public void setImagen(JButton Component, String nombreImg) {
        String rutaBase = "/Resources/" + nombreImg;
        ImageIcon instr = new ImageIcon(getClass().getResource(rutaBase));
        Image imginstr = instr.getImage();
        Image nuevaimagen = imginstr.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(nuevaimagen);
        Component.setIcon(imagen);
        add(Component);
    }
}
