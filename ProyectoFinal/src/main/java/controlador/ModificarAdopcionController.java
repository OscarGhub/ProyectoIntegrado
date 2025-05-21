package controlador;

import Dao.ModificarAdpDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.SolicitudAdopcion;
import modelo.Ventanas;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarAdopcionController {

    @FXML private TableView<SolicitudAdopcion> tablaModCitas;
    @FXML private TableColumn<SolicitudAdopcion, String> colCliente;
    @FXML private TableColumn<SolicitudAdopcion, String> colPerro;
    @FXML private TableColumn<SolicitudAdopcion, String> colFecha;
    @FXML private TableColumn<SolicitudAdopcion, String> colEstado;
    @FXML
    private ImageView imgProtectora;

    @FXML public void initialize() {
        modelo.Animaciones.animarAgrandar(imgProtectora);
        colCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCliente()));
        colPerro.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombrePerro()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaAlta()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        cargarDatos();
    }

    private void cargarDatos() {
        ObservableList<SolicitudAdopcion> lista = FXCollections.observableArrayList();

        String sql = """
            SELECT c.nombre AS cliente, p.nombre AS perro, sa.fecha_alta, sa.estado
            FROM solicitud_adopcion sa
            JOIN cliente c ON c.cliente_id = sa.cliente_id
            JOIN perro p ON p.perro_id = sa.perro_id
        """;

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new SolicitudAdopcion(
                        rs.getString("cliente"),
                        rs.getString("perro"),
                        rs.getDate("fecha_alta").toString(),
                        rs.getString("estado")
                ));
            }

            tablaModCitas.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void btnAceptarAdp(javafx.event.ActionEvent event) {
        SolicitudAdopcion selected = tablaModCitas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ModificarAdpDao.actualizarEstadoSolicitud(selected.getNombrePerro(), selected.getNombreCliente(), "aceptada");
            cargarDatos();
        }
    }

    @FXML void btnCancelarCita(javafx.event.ActionEvent event) {
        SolicitudAdopcion selected = tablaModCitas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ModificarAdpDao.actualizarEstadoSolicitud(selected.getNombrePerro(), selected.getNombreCliente(), "cancelada");
            cargarDatos();
        }
    }

    // Métodos vacíos de navegación
    @FXML void brnModificarPerrosAc(javafx.event.ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Modificar Perros");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @FXML void btnCitasAc(javafx.event.ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarCitas.fxml", "Modificar Citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @FXML void btnSalirAc(javafx.event.ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @FXML
    void imgProtectoraNotificaciones(MouseEvent event) {
        try {
            Ventanas.abrirVentana("/vista/notificacionesProtectora.fxml", "Notificaciones Protectora");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
