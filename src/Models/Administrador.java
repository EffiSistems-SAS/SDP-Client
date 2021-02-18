package Models;

import Connection.ConexionServer;
import com.google.gson.Gson;

public class Administrador extends Empleado {

    public int ingresarNuevoUsuario(Empleado usuario) {
        ConexionServer conection = ConexionServer.getConexionServer();
        Gson gson = new Gson();
        String req = gson.toJson(usuario, Empleado.class);
        int res = conection.POST("/adminOps/create/", req);
        return res;
    }

    public int eliminarUsuario(String correo) {
        ConexionServer conection = ConexionServer.getConexionServer();
        int res = conection.DELETE("/adminOps/delete/?correo=" + correo);
        return res;
    }

    public Empleado consultarEmpleado(String correo) {
        ConexionServer conection = ConexionServer.getConexionServer();
//        Empleado res = conection.GET("/adminOps/get/?correo=" + correo);
        return null;
    }

    public int editarEmpleado() {
        return 0;
    }

    public Empleado[] consultarListadoUsuarios() {
        return null;
    }
}
