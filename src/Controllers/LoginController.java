package Controllers;

import Models.Empleado;

public class LoginController {

    private Empleado usuario;

    public LoginController() {
        usuario = new Empleado();
    }

    public int iniciarSesion(String correo, String contraseña) {
        usuario.setCorreo(correo);
        usuario.setContraseña(contraseña);
        return usuario.iniciarSesion();
    }

}
