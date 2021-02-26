package Models;

public class Registro {

    private String _id;
    private String idEmpleado;
    private File idFile;
    private int __v;

    public File getIdFile() {
        return idFile;
    }

    @Override
    public String toString() {
        return "Struct{" + "_id=" + _id + ", idEmpleado=" + idEmpleado + ", idFile=" + idFile + ", __v=" + __v + '}';
    }

}
