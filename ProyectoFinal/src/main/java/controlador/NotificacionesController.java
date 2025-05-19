package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Ventanas;

public class NotificacionesController {

    @FXML
    private TableColumn<?, ?> notificaciones;

    @FXML
    private TableView<?> tablaNotificaciones;

    @FXML
    void btnVolver(ActionEvent event) {
        Ventanas.cerrarVentana(event);

    }

}