package controlador;

import Dao.HistorialAdopcionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import modelo.CitasInfo;
import modelo.SolicitudAdopcion;
import modelo.UsuarioSesion;
import modelo.Ventanas;
import utils.ConnectionManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialAdopcionesController {

    @FXML private TableView<SolicitudAdopcion> tablaModCitas;
    @FXML private TableColumn<SolicitudAdopcion, String> colCliente;
    @FXML private TableColumn<SolicitudAdopcion, String> colPerro;
    @FXML private TableColumn<SolicitudAdopcion, String> colFecha;
    @FXML private TableColumn<SolicitudAdopcion, String> colEstado;

    private final HistorialAdopcionDAO historialDAO = new HistorialAdopcionDAO();

    public void initialize() {
        colCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCliente()));
        colPerro.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombrePerro()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaAlta()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        cargarDatos();
    }

    @FXML
    private void cargarDatos() {
        ObservableList<SolicitudAdopcion> lista = FXCollections.observableArrayList();

        // Obtener id del cliente logueado
        int idCliente = UsuarioSesion.getUsuario().getIdUsuario();

        String sql = """
        SELECT c.nombre AS cliente, p.nombre AS perro, sa.fecha_alta, sa.estado
        FROM solicitud_adopcion sa
        JOIN cliente c ON c.cliente_id = sa.cliente_id
        JOIN perro p ON p.perro_id = sa.perro_id
        WHERE sa.cliente_id = ?
    """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new SolicitudAdopcion(
                            rs.getString("cliente"),
                            rs.getString("perro"),
                            rs.getDate("fecha_alta").toString(),
                            rs.getString("estado")
                    ));
                }
            }

            tablaModCitas.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verCitasCliente.fxml", "Ver Citas");
        } catch (Exception e) {
            Logger.getLogger(HistorialAdopcionesController.class.getName()).log(Level.SEVERE, "Error al volver", e);
        }
    }
}
