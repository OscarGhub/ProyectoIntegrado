package controlador;

import Dao.NotificacionesProtectoraDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.NotificacionProtectora;
import modelo.Ventanas;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificacionesProtectoraController implements Initializable {

    @FXML
    private TableView<NotificacionProtectora> tablaNotificaciones;

    @FXML
    private TableColumn<NotificacionProtectora, String> notificaciones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificaciones.setCellValueFactory(new PropertyValueFactory<>("informacion"));
        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        ObservableList<NotificacionProtectora> lista = NotificacionesProtectoraDao.obtenerNotificaciones();
        tablaNotificaciones.setItems(lista);
    }

    @FXML
    void btnVolver(ActionEvent event) {
        Ventanas.cerrarVentana(event);
    }
}
