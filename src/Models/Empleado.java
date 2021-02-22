package Models;

import Connection.ConexionServer;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    public int subirArchivos(Archivo archivo) {
        ConexionServer conection = ConexionServer.getConexionServer();
        int res;
        try {
            conection.POSTFILE("/multer/" + URLEncoder.encode(nombre, "UTF-8"), archivo.getFile());
            res = conection.POSTFILE("/files/post/" + id, archivo.getFile());
        } catch (UnsupportedEncodingException ex) {
            res = 400;
        }
        return res;
    }

    public InputStream bajarArchivos(String fileName) {
        ConexionServer conection = ConexionServer.getConexionServer();
        try {
            return conection.GETFILE("/files/get/" + URLEncoder.encode(nombre, "UTF-8") + "/" + fileName);
        } catch (UnsupportedEncodingException ex) {

        }
        return null;
    }

    public HistorialCambios consultarHistorialCambios(String fileName) {
        ConexionServer conection = ConexionServer.getConexionServer();
        String json = conection.GET("/files/getHistory/" + id);
        Gson gson = new Gson();
        HistorialCambios history = gson.fromJson(json, HistorialCambios.class);
        return history;
    }

    public int eliminarArchivo(String fileId) {
        ConexionServer conection = ConexionServer.getConexionServer();
        return conection.DELETE("/files/delete/" + id + "/" + fileId);
    }

    public Struct[] obtenerArchivos() {
        ConexionServer conection = ConexionServer.getConexionServer();
        String json = conection.GET("/files/get/" + id);
        Gson gson = new Gson();
        Struct[] files = gson.fromJson(json, Struct[].class);
        return files;
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
