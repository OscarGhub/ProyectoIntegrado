package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.CitasInfo;
import modelo.Ventanas;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitasController {

    @FXML private TableView<CitasInfo> tablaCitas;
    @FXML private TableColumn<CitasInfo, String> colEstado;
    @FXML private TableColumn<CitasInfo, Double> colDonacion;
    @FXML private TableColumn<CitasInfo, LocalDate> colFechaCita;
    @FXML private TableColumn<CitasInfo, String> colNombreCliente;
    @FXML private TableColumn<CitasInfo, String> colApellido1;
    @FXML private TableColumn<CitasInfo, String> colApellido2;
    @FXML private TableColumn<CitasInfo, String> colCorreoCliente;
    @FXML private TableColumn<CitasInfo, String> colCorreoUsuario;
    @FXML private TableColumn<CitasInfo, String> colNombrePerro;

    private final ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

    public void initialize() {
        // Configuramos las celdas de las columnas de la tabla
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colCorreoCliente.setCellValueFactory(new PropertyValueFactory<>("correoCliente"));
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoUsuario"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colNombrePerro.setCellValueFactory(new PropertyValueFactory<>("nombrePerro"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDonacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));


        cargarDatos();
    }

    @FXML
    private void cargarDatos() {
        listaCitas.clear(); // Limpiamos la lista antes de cargar nuevos datos

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##PROYECTOINTEGRADO";
        String password = "123456";

        String query = "SELECT c.donacion, c.estado, c.fecha_cita, "
                + "cli.nombre, cli.apellido1, cli.apellido2, cli.correo_electronico AS correo_cliente, "
                + "u.correo_electronico_cliente AS correo_usuario, p.nombre AS nombre_perro "
                + "FROM cita c "
                + "JOIN cliente cli ON c.cliente_id = cli.cliente_id "
                + "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id "
                + "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id "
                + "LEFT JOIN perro p ON sa.perro_id = p.perro_id";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int rowCount = 0;  // Para contar el número de columnas devueltas
            while (rs.next()) {
                CitasInfo citas = new CitasInfo(
                        rs.getDouble("donacion"),
                        rs.getString("estado"),
                        rs.getDate("fecha_cita").toLocalDate(),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("correo_cliente"),
                        rs.getString("correo_usuario"),
                        rs.getString("nombre_perro")
                );
                listaCitas.add(citas);
                rowCount++;
            }

            System.out.println("Columnas cargadas: " + rowCount);
            tablaCitas.setItems(listaCitas);

            if (rowCount == 0) {
                System.out.println("No se encontraron datos.");
            }

        } catch (SQLException e) {
            Logger.getLogger(CitasController.class.getName()).log(Level.SEVERE, "Error al cargar los datos de la base de datos", e);
        }
    }


    // Métodos de acción para los botones
    @FXML
    void btnSalirAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(CitasController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de inicio", e);
        }
    }

    @FXML
    void btnSolicitarAdpAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarAdopcion.fxml", "Solicitar adopción");
        } catch (Exception e) {
            Logger.getLogger(CitasController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de solicitud de adopción", e);
        }
    }

    @FXML
    void btnSolicitarCitasAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarCita.fxml", "Solicitar cita");
        } catch (Exception e) {
            Logger.getLogger(CitasController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de solicitud de cita", e);
        }
    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(CitasController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de ver perros", e);
        }
    }

    public TableColumn<CitasInfo, Double> getColDonacion() {
        return colDonacion;
    }

    public void setColDonacion(TableColumn<CitasInfo, Double> colDonacion) {
        this.colDonacion = colDonacion;
    }
}
