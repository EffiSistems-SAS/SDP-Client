package Controllers;

import Models.Empleado;

/**
 * @author User
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class LoginController {

    private Empleado usuario;

    public LoginController() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param correo
     * @param contrase�a
     */
    public int iniciarSesion(String correo, String contraseña) {
        Empleado empleado = new Empleado();
        empleado.setCorreo(correo);
        empleado.setContraseña(contraseña);
        return empleado.iniciarSesion();
    }

    public void cerrarSesion() {

    }
}//end LoginController
