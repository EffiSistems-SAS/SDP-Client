package Controllers;

import Views.MenuAdministrador;
import Models.Administrador;
import Models.Empleado;

public class AdministradorController {

    private Administrador administrador;
    private MenuAdministrador menu;
    public MenuAdministrador m_MenuAdministrador;
    public Administrador m_Administrador = new Administrador();

    public int crearUsuario(String nombre, String correo, String contraseña, String cargo, String rol) {
        Empleado model = new Empleado("", correo, contraseña, nombre, cargo, rol);
        int res = m_Administrador.ingresarNuevoUsuario(model);
        return res;
    }

    public int eliminarUsuario(String correo) {
        int res = m_Administrador.eliminarUsuario(correo);
        return res;
    }

    public int editarUsuario() {
        return 0;
    }

    public Empleado consultarEmpleado(String correo) {
        Empleado empleado = m_Administrador.consultarEmpleado(correo);
        if (empleado == null) {
            return null;
        }
        return empleado;
    }

    public Empleado[] getListadoUsuarios() {
        Empleado[] listado = m_Administrador.consultarListadoUsuarios();
        return listado;
    }

}
