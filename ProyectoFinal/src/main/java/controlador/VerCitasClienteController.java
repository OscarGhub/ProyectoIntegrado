package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.CitasInfo;
import modelo.Ventanas;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerCitasClienteController implements Initializable {

    @FXML private ImageView imgUsuario;
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
    @FXML private TableColumn<CitasInfo, Void> colModificar;

    @FXML private Button btnSolicitarAdp;
    @FXML private Button btnAjustes;
    @FXML private Button btnVerPerros;
    @FXML private Button btnSalir;

    private final ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);

        // Configurar las celdas de las columnas de la tabla
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNombrePerro.setCellValueFactory(new PropertyValueFactory<>("nombrePerro"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoUsuario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDonacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));

        // Configurar columna de botón de modificar
        colModificar.setCellFactory(col -> {
            TableCell<CitasInfo, Void> cell = new TableCell<>() {
                private final Button btn = new Button("Modificar");

                {
                    btn.setOnAction(event -> {
                        CitasInfo citaSeleccionada = getTableView().getItems().get(getIndex());
                        try {
                            abrirModificarCitaDialogo(citaSeleccionada);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        });

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
    private void editarCita(MouseEvent event) throws Exception {
        CitasInfo citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            // Llamar a un método para abrir la ventana de modificación de cita
            abrirModificarCitaDialogo(citaSeleccionada);
        } else {
            // Mostrar un mensaje de error si no hay cita seleccionada
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una cita para modificar.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void abrirModificarCitaDialogo(CitasInfo citaSeleccionada) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/modificarCitaCliente.fxml"));
        Parent root = loader.load();

        ModificarCitaClienteController modificarCitaClienteController = loader.getController();
        modificarCitaClienteController.setCitaSeleccionada(citaSeleccionada);

        // Mostrar la nueva ventana
        Stage stage = new Stage();
        stage.setTitle("Modificar Cita");
        stage.setScene(new Scene(root));
        stage.show();
    }


    // Método para guardar los cambios de la cita sin usar el ID
    @FXML
    private void guardarCambiosCita(CitasInfo cita) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##PROYECTOINTEGRADO";
        String password = "123456";

        // Realizamos una actualización basándonos en la fecha_cita y hora_cita
        String query = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                "WHERE fecha_cita = ? AND hora_cita = ? AND cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?) " +
                "AND perro_id = (SELECT perro_id FROM perro WHERE nombre = ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(2, cita.getHoraCita());
            pstmt.setString(3, cita.getEstado());

            // Condiciones para identificar la cita: fecha_cita, hora_cita, nombre_cliente y nombre_perro
            pstmt.setDate(4, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(5, cita.getHoraCita());
            pstmt.setString(6, cita.getNombreCliente());
            pstmt.setString(7, cita.getNombrePerro());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al actualizar la cita", e);
        }

        // Recargar los datos de la tabla
        cargarDatos();
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
    void btnSolicitarAdpAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarAdopcion.fxml", "Solicitar adopción");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de solicitud de adopción", e);
        }
    }

    @FXML
    void btnAjustesAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/ajustes.fxml", "Ajustes");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de solicitud de cita", e);
        }
    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de ver perros", e);
        }
    }

    public TableColumn<CitasInfo, Double> getColDonacion() {
        return colDonacion;
    }

    public void setColDonacion(TableColumn<CitasInfo, Double> colDonacion) {
        this.colDonacion = colDonacion;
    }
}
