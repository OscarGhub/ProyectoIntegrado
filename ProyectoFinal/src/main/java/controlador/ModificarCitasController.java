package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.CitasInfo;
import modelo.Ventanas;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarCitasController implements Initializable {

    @FXML
    private Button brnModificarPerros;

    @FXML private Button btnModificarCita;

    @FXML
    private Button btnSalir;

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

    @FXML
    private ImageView imgProtectora;

    @FXML
    void brnModificarPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Modificar perros");
        } catch (Exception e) {
            Logger.getLogger(ModificarPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSalirAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnModificarCitaAc(ActionEvent event) {
        CitasInfo citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/modificarCitaPopup.fxml"));
                Parent root = loader.load();

                ModificarCitaPopUpController popupController = loader.getController();
                popupController.setDatosCita(citaSeleccionada);

                Stage popupStage = new Stage();
                popupStage.setTitle("Modificar Cita");
                popupStage.setScene(new Scene(root));
                popupStage.showAndWait();

                cargarDatos();

            } catch (Exception e) {
                Logger.getLogger(ModificarCitasController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una cita para modificar.");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgProtectora);
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
    private void btnCancelarCitaAc(ActionEvent event) {
        CitasInfo citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                String user = "C##PROYECTOINTEGRADO";
                String password = "123456";

                // SQL UPDATE usando una combinación de fecha, hora y nombre del cliente
                String updateQuery = "UPDATE cita SET estado = ? WHERE fecha_cita = ? AND hora_cita = ? AND cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?)";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                    pstmt.setString(1, "Cancelada");
                    pstmt.setDate(2, Date.valueOf(citaSeleccionada.getFechaCita())); // convertir LocalDate a Date
                    pstmt.setString(3, citaSeleccionada.getHoraCita());
                    pstmt.setString(4, citaSeleccionada.getNombreCliente());

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        citaSeleccionada.setEstado("Cancelada");
                        tablaCitas.refresh(); // Refresca la tabla
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("La cita ha sido cancelada correctamente.");
                        alert.showAndWait();
                    } else {
                        throw new SQLException("No se encontró la cita para cancelar.");
                    }
                }

            } catch (SQLException e) {
                Logger.getLogger(ModificarCitasController.class.getName()).log(Level.SEVERE, null, e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al cancelar la cita.");
                alert.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecciona una cita para cancelar.");
            alerta.showAndWait();
        }
    }

}
