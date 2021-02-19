package Controllers;

import Connection.ConexionServer;
import Models.Archivo;
import Views.MenuEmpleado;
import Models.Empleado;
import com.google.gson.Gson;
import java.io.File;


public class EmpleadoController {

    private MenuEmpleado menu;
    public MenuEmpleado m_MenuEmpleado;
    public Empleado m_Empleado;

    public EmpleadoController() {
        m_Empleado = new Empleado();
    }
    
    public EmpleadoController(String correo){
        setInfo(correo);
    }
    
    public void setInfo(String correo){
        ConexionServer conection = ConexionServer.getConexionServer();
        String data = conection.GET("adminOps/get/"+correo);
        Gson gson = new Gson();
        System.out.println("Status: "+conection.getResponse().getStatusLine().getStatusCode());
        if(conection.getResponse().getStatusLine().getStatusCode() == 200){
            m_Empleado = gson.fromJson(data, Empleado.class);
           
        }
    }


    public Object verHistorialCambios() {
        return null;
    }

    public Object bajarArchivos() {
        return null;
    }

    public int subirArchivo(File archivo) {
        Archivo archivoSubida = new Archivo(archivo);
        return m_Empleado.subirArchivos(archivoSubida);
    }
}//end EmpleadoController
