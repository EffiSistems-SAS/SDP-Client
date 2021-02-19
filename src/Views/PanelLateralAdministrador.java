package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelLateralAdministrador extends JPanel {

    private final int Ancho = 200, Alto = 515;

    private JButton BtnCreEmp, BtnDelEmp, BtnUpdEmp, BtnListEmp, BtnSearchEmp;
    private PanelCentralAdministrador RefCentral;

    private Color AzulOscuro = new Color(51, 80, 101);

    public PanelLateralAdministrador(PanelCentralAdministrador ref) {
        initTemplate();
        RefCentral = ref;
    }

    private void initComponents() {
        BtnCreEmp = new JButton("Crear empleado");
        BtnCreEmp.setSize(new Dimension(190, 40));
        BtnCreEmp.setLocation(new Point(5, 5));
        BtnCreEmp.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnCreEmp.setBackground(AzulOscuro);
        BtnCreEmp.setForeground(Color.WHITE);
        BtnCreEmp.setFocusable(false);
        add(BtnCreEmp);

        BtnDelEmp = new JButton("Eliminar empleado");
        BtnDelEmp.setSize(new Dimension(190, 40));
        BtnDelEmp.setLocation(new Point(5, 120));
        BtnDelEmp.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnDelEmp.setBackground(AzulOscuro);
        BtnDelEmp.setForeground(Color.WHITE);
        BtnDelEmp.setFocusable(false);
        add(BtnDelEmp);

        BtnUpdEmp = new JButton("Editar empleado");
        BtnUpdEmp.setSize(new Dimension(190, 40));
        BtnUpdEmp.setLocation(new Point(5, 235));
        BtnUpdEmp.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnUpdEmp.setBackground(AzulOscuro);
        BtnUpdEmp.setForeground(Color.WHITE);
        BtnUpdEmp.setFocusable(false);
        add(BtnUpdEmp);

        BtnListEmp = new JButton("Mostrar empleados");
        BtnListEmp.setSize(new Dimension(190, 40));
        BtnListEmp.setLocation(new Point(5, 350));
        BtnListEmp.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnListEmp.setBackground(AzulOscuro);
        BtnListEmp.setForeground(Color.WHITE);
        BtnListEmp.setFocusable(false);
        add(BtnListEmp);

        BtnSearchEmp = new JButton("Buscar empleado");
        BtnSearchEmp.setSize(new Dimension(190, 40));
        BtnSearchEmp.setLocation(new Point(5, 470));
        BtnSearchEmp.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnSearchEmp.setBackground(AzulOscuro);
        BtnSearchEmp.setForeground(Color.WHITE);
        BtnSearchEmp.setFocusable(false);
        add(BtnSearchEmp);
    }

    private void initListeners() {
        BtnCreEmp.addActionListener((e) -> {
            RefCentral.insertarEmpleado();
        });

        BtnDelEmp.addActionListener((e) -> {
            RefCentral.eliminarEmpleado();
        });

        BtnUpdEmp.addActionListener((e) -> {
            RefCentral.editarEmpleado();
        });

        BtnListEmp.addActionListener((e) -> {
            RefCentral.listarEmpleados();
        });

        BtnSearchEmp.addActionListener((e) -> {
            RefCentral.buscarEmpleado();
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
