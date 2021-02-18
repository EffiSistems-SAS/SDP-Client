package Controllers;

import Views.MenuAdministrador;
import Models.Administrador;
import Models.Empleado;

/**
 * @author User
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class AdministradorController {

    private Administrador administrador;
    private MenuAdministrador menu;
    public MenuAdministrador m_MenuAdministrador;
    public Administrador m_Administrador = new Administrador();

    public AdministradorController() {

    }

    public void finalize() throws Throwable {

    }

    public Object getListadoUsuarios() {
        return null;
    }

    /**
     *
     * @param idEmpleado
     */
    public int eliminarUsuario(String correo) {
        int res = m_Administrador.eliminarUsuario(correo);
        return res;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param correo
     * @param contrase�a
     */
    public int crearUsuario(String nombre, String correo, String contraseña, String cargo, String rol) {
        Empleado model = new Empleado("", correo, contraseña, nombre, cargo, rol);
        int res = m_Administrador.ingresarNuevoUsuario(model);
        return res;
    }
}//end AdministradorController
