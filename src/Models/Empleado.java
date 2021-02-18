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
    private String contraseña;
    private String nombre;
    private ConexionServer m_ConexionServer;
    private Archivo m_Archivo;

    public Empleado() {

    }

    public int iniciarSesion() {
        ConexionServer conection = ConexionServer.getConexionServer();
        int res = conection.GET("/auth/login/" + correo + "/" + contraseña);
        return res;
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
        this.contraseña = contraseña;
    }

}//end Empleado
