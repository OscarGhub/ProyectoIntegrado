package controlador;

import Dao.*;
import Dao.PerroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Perro;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarPerrosController implements Initializable {

    @FXML
    private Button brnModificarCitas;

    @FXML
    private Button brnModificarCitas1;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnAgregarPerro;

    @FXML
    private Button btnEliminarPerro;

    @FXML
    private TableColumn<Perro, String> colNombre;

    @FXML
    private TableColumn<Perro, String> colSexo;

    @FXML
    private TableColumn<Perro, Object> colFechaNacimiento;

    @FXML
    private TableView<Perro> tabla;

    @FXML
    private ImageView imgProtectora;

    private ObservableList<Perro> listaPerros;

    private final Dao.ModificarPerrosDao perroDAO = new Dao.ModificarPerrosDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgProtectora);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSexo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSexoComoString()));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_alta"));

        cargarPerros();
    }

    private void cargarPerros() {
        listaPerros = FXCollections.observableArrayList(perroDAO.obtenerTodosLosPerros());
        tabla.setItems(listaPerros);
    }

    @FXML
    void brnModificarCitasAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarCitas.fxml", "Modificar citas");
        } catch (Exception e) {
            Logger.getLogger(ModificarCitasController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnAdpAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/modificarAdopcion.fxml", "Modificar Adopción");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
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
    void verNotificaciones(MouseEvent event) {
        try {
            Ventanas.abrirVentana("/vista/notificacionesProtectora.fxml", "Notificaciones Protectora");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnAgregarPerro(ActionEvent event) {
        // Aquí podrías abrir un formulario para agregar un perro nuevo
        // Ventanas.abrirVentana("/vista/agregarPerro.fxml", "Agregar Perro");
    }

    @FXML
    void btnEliminarPerro(ActionEvent event) {
        Perro perroSeleccionado = tabla.getSelectionModel().getSelectedItem();

        if (perroSeleccionado != null) {
            boolean eliminado = Dao.ModificarPerrosDao.eliminarPerroPorId(perroSeleccionado.getPerro_id());

            if (eliminado) {
                listaPerros.remove(perroSeleccionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Perro eliminado", "El perro ha sido eliminado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al eliminar", "No se pudo eliminar el perro.");
            }

        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Ninguna selección", "Debes seleccionar un perro para eliminar.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
