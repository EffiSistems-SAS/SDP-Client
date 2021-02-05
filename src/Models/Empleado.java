package Models;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Empleado extends Usuario {

    public Archivo m_Archivo;

    public Empleado() {

    }

    public void finalize() throws Throwable {
        super.finalize();
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
}//end Empleado
