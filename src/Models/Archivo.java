package Models;

import java.io.File;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Archivo {

    private HistorialCambios[] historialDeCambios;
    private File file;

    public Archivo(File file) {
        this.file = file;
    }
    
    public File getFile(){
        return file;
    }
    
    public HistorialCambios[] getHistorialCambios(){
        return historialDeCambios;
    }

}//end Archivo
