package controlador;

import Dao.CitasDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.CitasInfo;
import modelo.Ventanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
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

    private CitasDAO citasDAO = new CitasDAO(); // Instancia de CitasDAO

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

        cargarDatos(); // Cargar los datos de las citas
    }

    @FXML
    private void cargarDatos() {
        listaCitas.clear();
        listaCitas.addAll(citasDAO.obtenerCitas()); // Usar CitasDAO para cargar las citas
        tablaCitas.setItems(listaCitas);
    }

    @FXML
    private void guardarCambiosCita(CitasInfo cita) {
        if (citasDAO.cancelarCita(cita)) { // Usar CitasDAO para cancelar la cita
            cargarDatos(); // Recargar los datos después de la actualización
        } else {
            // Mostrar mensaje de error si no se pudo cancelar
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo cancelar la cita.", ButtonType.OK);
            alert.showAndWait();
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

}
