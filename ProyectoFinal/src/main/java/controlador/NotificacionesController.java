package controlador;

import Dao.NotificacionesClienteDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Notificaciones;
import modelo.Notificaciones;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificacionesController implements Initializable {

    @FXML
    private TableView<Notificaciones> tablaNotificaciones;

    @FXML
    private TableColumn<Notificaciones, String> notificaciones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificaciones.setCellValueFactory(new PropertyValueFactory<>("informacion"));
        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        ObservableList<Notificaciones> lista = NotificacionesClienteDao.obtenerNotificaciones();
        tablaNotificaciones.setItems(lista);
    }

    @FXML
    void btnVolver(ActionEvent event) {
        Ventanas.cerrarVentana(event);
    }
}
