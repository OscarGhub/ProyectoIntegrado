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

public class HistorialCitasController implements Initializable {

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

    @FXML private Button btnVolver;

    private final ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

    private final CitasDAO citasDAO = new CitasDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);

        // Asignar valores a las columnas de la tabla
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNombrePerro.setCellValueFactory(new PropertyValueFactory<>("nombrePerro"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoUsuario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDonacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));
        colModificar.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Modificar");
            {
                btn.setOnAction(event -> {
                    CitasInfo cita = getTableView().getItems().get(getIndex());
                    try {
                        abrirModificarCitaDialogo(cita);
                    } catch (Exception e) {
                        Logger.getLogger(HistorialCitasController.class.getName()).log(Level.SEVERE, null, e);
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        cargarDatos();
    }

    @FXML
    private void cargarDatos() {
        listaCitas.clear();
        try {
            listaCitas.addAll(citasDAO.obtenerCitas());
        } catch (Exception e) {
            Logger.getLogger(HistorialCitasController.class.getName()).log(Level.SEVERE, "Error al cargar citas", e);
            // Aquí podrías mostrar una alerta al usuario
        }
        tablaCitas.setItems(listaCitas);
    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(HistorialCitasController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de ver perros", e);
        }
    }

    private void abrirModificarCitaDialogo(CitasInfo citaSeleccionada) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/modificarCitaCliente.fxml"));
        Parent root = loader.load();

        ModificarCitaClienteController controller = loader.getController();
        controller.setCitaSeleccionada(citaSeleccionada);

        Stage stage = new Stage();
        stage.setTitle("Modificar Cita");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
