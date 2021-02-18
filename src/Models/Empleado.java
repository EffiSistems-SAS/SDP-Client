package Models;

import Connection.ConexionServer;

public class Empleado {

    private String id;
    private String correo;
    private String contrasena;
    private String nombre;
    private String cargo;
    private String rol;
    private ConexionServer m_ConexionServer;
    private Archivo m_Archivo;

    public Empleado() {

    }

    public Empleado(String id, String correo, String contraseña, String nombre, String cargo, String rol) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contraseña;
        this.nombre = nombre;
        this.cargo = cargo;
        this.rol = rol;
    }

    public int iniciarSesion() {
        ConexionServer conection = ConexionServer.getConexionServer();
        conection.GET("/auth/login/" + correo + "/" + contrasena);
        return conection.getResponse().getStatusLine().getStatusCode();
    }

    public void subirArchivos(Archivo[] archivos) {

    }

    public Archivo[] bajarArchivos(Archivo[] archivos) {
        return null;
    }

    public HistorialCambios[] consultarHistorialCambios(Archivo archivo) {
        return null;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", correo=" + correo + ", contrasena=" + contrasena + ", nombre=" + nombre + ", cargo=" + cargo + ", rol=" + rol + ", m_ConexionServer=" + m_ConexionServer + ", m_Archivo=" + m_Archivo + '}';
    }

}
