package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NotificacionProtectora {
    private final StringProperty informacion;

    public NotificacionProtectora(String informacion) {
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
