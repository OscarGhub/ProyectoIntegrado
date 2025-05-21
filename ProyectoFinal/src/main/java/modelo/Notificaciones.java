package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notificaciones {
    private final StringProperty informacion;

    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Notificaciones(String informacion) {
        this.informacion = new SimpleStringProperty(informacion);
    }

    public String getInformacion() {
        return informacion.get();
    }

    public void setInformacion(String informacion) {
        this.informacion.set(informacion);
    }

    public StringProperty informacionProperty() {
        return informacion;
    }
}
