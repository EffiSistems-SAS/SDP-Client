package Models;

import java.util.ArrayList;

public class HistorialCambios {

    ArrayList<Cambio> cambios = new ArrayList<>();

    public ArrayList<Cambio> getCambios() {
        return cambios;
    }

    public void setCambios(ArrayList<Cambio> cambios) {
        this.cambios = cambios;
    }

    @Override
    public String toString() {
        String retorno = "";

        for (int i = 0; i < cambios.size(); i++) {
            Cambio currentChange = cambios.get(i);
            retorno += "Cambio #" + (i + 1) + " Versión: " + currentChange.getVersion() + " ----- Fecha: " + currentChange.getFecha() + "\n";
        }

        return retorno;
    }

}
