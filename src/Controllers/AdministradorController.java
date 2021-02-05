package Controllers;

import Views.MenuAdministrador;
import Models.Administrador;

/**
 * @author User
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class AdministradorController {

    private Administrador administrador;
    private MenuAdministrador menu;
    public MenuAdministrador m_MenuAdministrador;
    public Administrador m_Administrador;

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
    public int eliminarUsuario(int idEmpleado) {
        return 0;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param correo
     * @param contrase�a
     */
    public int crearUsuario(int id, String nombre, String correo, String contraseña) {
        return 0;
    }
}//end AdministradorController
