package Controllers;

import Connection.ConexionServer;
import Models.Archivo;
import Views.MenuEmpleado;
import Models.Empleado;
import Models.HistorialCambios;
import Models.Struct;
import com.google.gson.Gson;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EmpleadoController {
    
    private Empleado m_Empleado;
    
    private static final int CHUNK_SIZE = 1024 * 4;
    
    public EmpleadoController() {
        m_Empleado = new Empleado();
    }
    
    public EmpleadoController(String correo) {
        setInfo(correo);
    }
    
    public void setInfo(String correo) {
        ConexionServer conection = ConexionServer.getConexionServer();
        String data = conection.GET("/adminOps/get/" + correo);
        Gson gson = new Gson();
        if (conection.getResponse().getStatusLine().getStatusCode() == 200) {
            m_Empleado = gson.fromJson(data, Empleado.class);
        }
    }
    
    public HistorialCambios verHistorialCambios(String fileName) {
        HistorialCambios historial = m_Empleado.consultarHistorialCambios(fileName);
        return historial;
    }
    
    public int eliminarArchivo(String fileName) {
        int status = m_Empleado.eliminarArchivo(fileName);
        return status;
    }
    
    public Models.File[] obtenerArchivos() {
        Struct[] structs = m_Empleado.obtenerArchivos();
        Models.File[] files = new Models.File[structs.length];
        for (int i = 0; i < structs.length; i++) {
            files[i] = structs[i].getIdFile();
        }
        return files;
    }
    
    public int bajarArchivo(String nameFile) {
        InputStream newInput = m_Empleado.bajarArchivos(nameFile);
        String rutaBase = System.getProperty("user.home") + "\\Downloads";
        String rutaDir = rutaBase + "\\" + m_Empleado.getNombre();
        String rutaFile = rutaDir + "\\" + nameFile;
        
        File fileDir = new File(rutaDir);
        File file = new File(rutaFile);
        if (!(newInput == null)) {
            try {
                if (!(fileDir.exists())) {
                    if (fileDir.mkdir()) {
                        crearArchivo(rutaFile, newInput);
                        if (file.exists()) {
                            return 200;
                        } else {
                            return 403;
                        }
                    } else {
                        return 402;
                    }
                } else {
                    crearArchivo(rutaFile, newInput);
                    if (file.exists()) {
                        return 200;
                    } else {
                        return 403;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
                return 401;
            }
        } else {
            return 400;
        }
    }
    
    public int subirArchivo(File archivo) {
        Archivo archivoSubida = new Archivo(archivo);
        return m_Empleado.subirArchivos(archivoSubida);
    }
    
    public void crearArchivo(String ruta, InputStream is) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(ruta)));
        byte[] chunk = new byte[CHUNK_SIZE];
        int bytesLeidos = 0;
        while ((bytesLeidos = is.read(chunk)) > 0) {
            os.write(chunk, 0, bytesLeidos);
        }
        os.close();
    }
    
    public Empleado getEmpleado() {
        return m_Empleado;
    }
}
