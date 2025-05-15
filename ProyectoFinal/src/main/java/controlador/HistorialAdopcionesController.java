package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import modelo.CitasInfo;
import modelo.Ventanas;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialAdopcionesController {
    @FXML
    private ImageView imgUsuario;
    @FXML private TableView<CitasInfo> tablaCitas;
    @FXML private TableColumn<CitasInfo, String> colEstado;
    @FXML private TableColumn<CitasInfo, Double> colDonacion;
    @FXML private TableColumn<CitasInfo, LocalDate> colFechaCita;
    @FXML private TableColumn<CitasInfo, String> colHoraCita;
    @FXML private TableColumn<CitasInfo, String> colNombreCliente;
    @FXML private TableColumn<CitasInfo, String> colApellido1;
    @FXML private TableColumn<CitasInfo, String> colApellido2;
    @FXML private TableColumn<CitasInfo, String> colCorreoUsuario;
    @FXML private TableColumn<CitasInfo, String> colNombrePerro;



    private final ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {

        modelo.Animaciones.animarAgrandar(imgUsuario);

        // Configuramos las celdas de las columnas de la tabla
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNombrePerro.setCellValueFactory(new PropertyValueFactory<>("nombrePerro"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoUsuario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDonacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));


        cargarDatos();
    }

    @FXML
    private void cargarDatos() {
        listaCitas.clear();

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##PROYECTOINTEGRADO";
        String password = "123456";

        String query = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2," +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CitasInfo citas = new CitasInfo(
                        rs.getDouble("donacion"),
                        rs.getString("estado"),
                        rs.getDate("fecha_cita").toLocalDate(),
                        rs.getString("hora_cita"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("correo_usuario"),
                        rs.getString("nombre_perro")
                );
                listaCitas.add(citas);
            }
            tablaCitas.setItems(listaCitas);

        } catch (SQLException e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al cargar los datos de la base de datos", e);
        }

    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verCitasCliente.fxml", "Ver Citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de ver perros", e);
        }
    }
}
