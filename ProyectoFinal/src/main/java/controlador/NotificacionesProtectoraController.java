package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.NotificacionProtectora;
import modelo.Ventanas;
import utils.ConnectionManager;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class NotificacionesProtectoraController implements Initializable {

    @FXML
    private TableView<NotificacionProtectora> tablaNotificaciones;

    @FXML
    private TableColumn<NotificacionProtectora, String> notificaciones;

    private ObservableList<NotificacionProtectora> listaNotificaciones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificaciones.setCellValueFactory(new PropertyValueFactory<>("informacion"));
        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        listaNotificaciones = FXCollections.observableArrayList();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            String sql = "SELECT informacion FROM notificaciones_protectora";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String info = resultSet.getString("informacion");
                listaNotificaciones.add(new NotificacionProtectora(info));
            }

            tablaNotificaciones.setItems(listaNotificaciones);

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores según sea necesario
        }
    }
    @FXML
    void btnVolver(ActionEvent event) {
        Ventanas.cerrarVentana(event);

    }
}
