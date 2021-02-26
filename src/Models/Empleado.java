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
        m_ConexionServer = ConexionServer.getConexionServer();
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
        m_ConexionServer.GET("/auth/login/" + correo + "/" + contrasena);
        return m_ConexionServer.getResponse().getStatusLine().getStatusCode();
    }

    public int subirArchivos(Archivo archivo) {
        int res;
        try {
            m_ConexionServer.POSTFILE("/multer/" + URLEncoder.encode(nombre, "UTF-8"), archivo.getFile());
            res = m_ConexionServer.POSTFILE("/files/post/" + id+"/"+URLEncoder.encode(archivo.getFile().getName(), "UTF-8"), archivo.getFile());
        } catch (UnsupportedEncodingException ex) {
            res = 400;
        }
        return res;
    }

    public InputStream bajarArchivos(String fileName) {
        try {
            return m_ConexionServer.GETFILE("/files/download/" + URLEncoder.encode(nombre, "UTF-8") + "/" + fileName);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public HistorialCambios consultarHistorialCambios(String idFile) {
        String json = m_ConexionServer.GET("/historialCambios/" + idFile);
        Gson gson = new Gson();
        HistorialCambios history = gson.fromJson(json, HistorialCambios.class);
        return history;
    }

    public int eliminarArchivo(String fileId,String fileName) {
        int resFisica = 0,resMongo = 0;
        try {
            resFisica = m_ConexionServer.DELETE("/multer/"+"delete/"+URLEncoder.encode(nombre, "UTF-8")+"/?fileName="+URLEncoder.encode(fileName, "UTF-8"));
            resMongo = m_ConexionServer.DELETE("/files/delete/" + id + "/" + fileId);
        } catch (UnsupportedEncodingException ex) {
        }
        return (resFisica == 200 && resMongo == 200)?(200):(400);
    }

    public Registro[] obtenerArchivos() {
        String json = m_ConexionServer.GET("/files/get/" + id);
        Gson gson = new Gson();
        Registro[] files = gson.fromJson(json, Registro[].class);
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
