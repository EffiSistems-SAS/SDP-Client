package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MenuAdministrador extends JFrame {
    
    private final int ANCHO, ALTO;

    private JPanel panelCentral;
    private JLabel LblHora = new JLabel();
    private JButton BtnCrear, BuscarUsuaio, BtnEliminar, BtnListar,BtnQuit;
   
    private Timer timer = new Timer(1000, (e) -> {
        LocalDateTime a = LocalDateTime.now();
        LblHora.setText("<html><body><center style='font-size:20px'>Hora actual<br>" + a.getDayOfMonth() + " / "+ a.getMonthValue() + " / " + a.getYear() + "<br>" + a.getHour() + " : " + a.getMinute() + " : " + a.getSecond() + "</center></body></html>");
        repaint();
    });
    private String name;

    public MenuAdministrador(String name) {
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

        BtnCrear = new JButton("Crear usuario");
        BtnCrear.setSize(150, 30);
        BtnCrear.setLocation(15, 60);
        BtnCrear.setFont(new Font("Arial",Font.PLAIN,15));
        BtnCrear.setFocusable(false);
        add(BtnCrear);

        BuscarUsuaio = new JButton("Buscar Usuario");
        BuscarUsuaio.setSize(150, 30);
        BuscarUsuaio.setLocation(15, 200);
        BuscarUsuaio.setFont(new Font("Arial",Font.PLAIN,15));
        BuscarUsuaio.setFocusable(false);
        add(BuscarUsuaio);

        BtnEliminar = new JButton("Eliminar usuario");
        BtnEliminar.setSize(150, 30);
        BtnEliminar.setLocation(getWidth() - BtnEliminar.getWidth() - 20, 60);
        BtnEliminar.setFont(new Font("Arial",Font.PLAIN,15));
        BtnEliminar.setFocusable(false);
        add(BtnEliminar);

        BtnListar = new JButton("Listar usuarios");
        BtnListar.setSize(BtnEliminar.getWidth(), 30);
        BtnListar.setLocation(getWidth() - BtnListar.getWidth() - 20, 200);
        BtnListar.setFont(new Font("Arial",Font.PLAIN,15));
        BtnListar.setFocusable(false);
        add(BtnListar);
        
        BtnQuit = new JButton("Cerrar sesión");
        BtnQuit.setSize(150, 30);
        BtnQuit.setLocation((getWidth() - BtnQuit.getWidth())/2, 230);
        BtnQuit.setFont(new Font("Arial",Font.PLAIN,15));
        BtnQuit.setFocusable(false);
        add(BtnQuit);
    }


    public void initListeners() {
        BtnCrear.addActionListener(ae -> {
            
        });

        BuscarUsuaio.addActionListener(ae -> {
            

        });

        BtnEliminar.addActionListener(ae -> {
           
        });
        
        BtnListar.addActionListener((event) -> {
            
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
        setLocationRelativeTo(null);
        initComponents();
        initListeners();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
}