package controlador;

import Dao.HistorialAdopcionDAO;
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialAdopcionesController {

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

    private final HistorialAdopcionDAO historialDAO = new HistorialAdopcionDAO();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);

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
        ObservableList<CitasInfo> citas = historialDAO.obtenerHistorialAdopciones();
        tablaCitas.setItems(citas);
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
