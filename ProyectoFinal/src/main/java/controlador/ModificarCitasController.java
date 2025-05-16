package controlador;

import Dao.CitasDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.CitasInfo;
import modelo.Ventanas;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarCitasController implements Initializable {

    @FXML private Button brnModificarPerros;
    @FXML private Button btnModificarCita;
    @FXML private Button btnSalir;
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
    @FXML private ImageView imgProtectora;

    private final ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();
    private final CitasDAO citasDAO = new CitasDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgProtectora);

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
        listaCitas.setAll(citasDAO.obtenerCitas());
        tablaCitas.setItems(listaCitas);
    }

    @FXML
    private void brnModificarPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Modificar perros");
        } catch (Exception e) {
            Logger.getLogger(ModificarPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnSalirAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnModificarCitaAc(ActionEvent event) {
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
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Selecciona una cita para modificar.");
        }
    }

    @FXML
    private void btnCancelarCitaAc(ActionEvent event) {
        CitasInfo citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            boolean exito = citasDAO.cancelarCita(citaSeleccionada);
            if (exito) {
                citaSeleccionada.setEstado("Cancelada");
                tablaCitas.refresh();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Confirmación", "La cita ha sido cancelada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al cancelar la cita.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Por favor, selecciona una cita para cancelar.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Button getBtnModificarCita() {
        return btnModificarCita;
    }

    public void setBtnModificarCita(Button btnModificarCita) {
        this.btnModificarCita = btnModificarCita;
    }

    public Button getBrnModificarPerros() {
        return brnModificarPerros;
    }

    public void setBrnModificarPerros(Button brnModificarPerros) {
        this.brnModificarPerros = brnModificarPerros;
    }

    public Button getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(Button btnSalir) {
        this.btnSalir = btnSalir;
    }
}
