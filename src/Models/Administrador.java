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
        int res = conection.DELETE("/adminOps/delete/" + correo);
        return res;
    }

    public Empleado consultarEmpleado(String correo) {
        ConexionServer conection = ConexionServer.getConexionServer();

        Gson gson = new Gson();
        String info = conection.GET("/adminOps/get/" + correo);
        if (info.equals("Empleado no encontrado")) {
            return null;
        } else {
            Empleado res = gson.fromJson(info, Empleado.class);
            return res;
        }

    }

    public int editarEmpleado(Empleado empleado) {
        ConexionServer conection = ConexionServer.getConexionServer();
        Gson gson = new Gson();
        String datos = gson.toJson(empleado);
        return conection.PUT("/adminOps/edit", datos);
    }

    public Empleado[] consultarListadoUsuarios() {
        ConexionServer conection = ConexionServer.getConexionServer();
        Gson gson = new Gson();
        String info = conection.GET("/adminOps/list/");
        Empleado[] lista = gson.fromJson(info, Empleado[].class);
        return lista;
    }
}
