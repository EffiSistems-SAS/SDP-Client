package Models;

import Connection.ConexionServer;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Empleado {

    private String id;
    private String correo;
    private String contrasena;
    private String nombre;
    private String cargo;
    private String rol;
    private ConexionServer m_ConexionServer;
    private Archivo m_Archivo;

    public Empleado(){
        
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

    /**
     *
     * @param archivos
     */
    public void subirArchivos(Archivo[] archivos) {

    }

    /**
     *
     * @param archivos
     */
    public Archivo[] bajarArchivos(Archivo[] archivos) {
        return null;
    }

    /**
     *
     * @param archivo
     */
    public HistorialCambios[] consultarHistorialCambios(Archivo archivo) {
        return null;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

}//end Empleado
