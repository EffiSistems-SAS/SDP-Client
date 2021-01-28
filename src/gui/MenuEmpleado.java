package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;


public class MenuEmpleado extends JFrame {
    
    private final int ANCHO, ALTO;

    private JPanel panelCentral;
    private JLabel LblHora = new JLabel();
    private JButton BtnSubir, BtnBajar, BtnHistorial, BtnQuit;
   
    private Timer timer = new Timer(1000, (e) -> {
        LocalDateTime a = LocalDateTime.now();
        LblHora.setText("<html><body><center style='font-size:20px'>Hora actual<br>" + a.getDayOfMonth() + " / "+ a.getMonthValue() + " / " + a.getYear() + "<br>" + a.getHour() + " : " + a.getMinute() + " : " + a.getSecond() + "</center></body></html>");
        repaint();
    });
    private String name;

    public MenuEmpleado(String name) {
        ANCHO = 700;
        ALTO = 300;
        timer.start();
        this.name = name;
    }

    public void initComponents() {

        panelCentral = new JPanel();
        panelCentral.setSize(300, 205);
        panelCentral.setLocation((getWidth() - panelCentral.getWidth()) / 2, 20);
        panelCentral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentral.setLayout(null);
        add(panelCentral);

        LblHora.setSize(200, 100);
        LblHora.setLocation((panelCentral.getWidth() - LblHora.getWidth()) / 2, 40);
        LblHora.setHorizontalAlignment(JLabel.CENTER);
        LblHora.setFont(new Font("Arial", Font.PLAIN, 17));
        LblHora.setBackground(new Color(47, 53, 58));
        panelCentral.add(LblHora);

        BtnSubir = new JButton("Subir archivos");
        BtnSubir.setSize(140, 30);
        BtnSubir.setLocation(15, 60);
        BtnSubir.setFont(new Font("Arial",Font.PLAIN,15));
        BtnSubir.setFocusable(false);
        BtnSubir.setBackground(new Color(0, 37, 63));
        BtnSubir.setForeground(Color.WHITE);
        add(BtnSubir);

        BtnBajar = new JButton("Bajar archivos");
        BtnBajar.setSize(140, 30);
        BtnBajar.setLocation(15, 200);
        BtnBajar.setFont(new Font("Arial",Font.PLAIN,15));
        BtnBajar.setFocusable(false);
        BtnBajar.setBackground(new Color(0, 37, 63));
        BtnBajar.setForeground(Color.WHITE);
        add(BtnBajar);

        BtnHistorial = new JButton("Ver historial");
        BtnHistorial.setSize(125, 30);
        BtnHistorial.setLocation(getWidth() - BtnHistorial.getWidth() - 20, 60);
        BtnHistorial.setFont(new Font("Arial",Font.PLAIN,15));
        BtnHistorial.setFocusable(false);
        BtnHistorial.setBackground(new Color(0, 37, 63));
        BtnHistorial.setForeground(Color.WHITE);
        add(BtnHistorial);

        BtnQuit = new JButton("Cerrar sesión");
        BtnQuit.setSize(BtnHistorial.getWidth(), 30);
        BtnQuit.setLocation(getWidth() - BtnQuit.getWidth() - 20, 200);
        BtnQuit.setFont(new Font("Arial",Font.PLAIN,15));
        BtnQuit.setFocusable(false);
        BtnQuit.setBackground(new Color(0, 37, 63));
        BtnQuit.setForeground(Color.WHITE);
        add(BtnQuit);
    }


    public void initListeners() {
        BtnSubir.addActionListener(ae -> {
            
        });

        BtnBajar.addActionListener(ae -> {
            

        });

        BtnHistorial.addActionListener(ae -> {
           
        });
        
        BtnQuit.addActionListener(ae -> {
            dispose();
            Login newLogin = new Login();
            newLogin.initTemplate();
        });
    }
    
    public void initTemplate() {
        setLayout(null);
        setTitle("Bienvenido "+ this.name);
        setSize(new Dimension(ANCHO, ALTO));
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        initComponents();
        initListeners();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
}
