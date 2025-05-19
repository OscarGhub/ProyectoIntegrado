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
import modelo.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dao.PerroDAO;




public class SolicitarAdpController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnAjustes;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnVerPerros;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private Button btnSolicitarAdopcion;

    @FXML private TableView<Perro> tablaPerros;
    @FXML private TableColumn<Perro, String> colNombre;
    @FXML private TableColumn<Perro, String> colRaza;
    @FXML private TableColumn<Perro, String> colSexo;
    @FXML private TableColumn<Perro, String> colFechaAlta;

    private final ObservableList<Perro> listaPerros = FXCollections.observableArrayList();
    private Usuario usuarioLogueado;

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        System.out.println("Usuario recibido en SolicitarAdpController: " + usuario);
    }

    @FXML
    public void enviarFormulario(ActionEvent event) {
        Perro perroSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();

        if (perroSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida",
                    "Por favor, selecciona un perro de la tabla antes de solicitar adopción.");
            return;
        }

        if (usuarioLogueado == null) {
            usuarioLogueado = UsuarioSesion.getUsuario();
            if (usuarioLogueado == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de sesión",
                        "No hay información del usuario logueado. Por favor, vuelve a iniciar sesión.");
                return;
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/formularioAdopcion.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del formulario
            FormularioAdopcionController controller = loader.getController();

            // PASAR LOS DATOS DEL PERRO Y DEL USUARIO LOGUEADO
            controller.setNombrePerro(perroSeleccionado.getNombre());
            controller.setUsuarioLogueado(usuarioLogueado);
            controller.setCorreoCliente(usuarioLogueado.getCorreoElectronico());

            Stage stage = new Stage();
            stage.setTitle("Formulario de Adopción");
            stage.setScene(new Scene(root));
            stage.setWidth(600);
            stage.setHeight(400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error",
                    "No se pudo cargar el formulario de adopción: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarPerrosDisponibles() {
        ObservableList<Perro> listaPerros = PerroDAO.obtenerPerrosDisponibles();
        tablaPerros.setItems(listaPerros);
    }

    @FXML
    public void handleTableClick(javafx.scene.input.MouseEvent event) {
        if (event.getClickCount() == 2) {
            enviarFormulario(new ActionEvent());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);

        // Configurar columnas
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fecha_alta"));

        cargarPerrosDisponibles();

        if (usuarioLogueado == null) {
            Usuario userSesion = UsuarioSesion.getUsuario();
            if (userSesion != null) {
                usuarioLogueado = userSesion;
                System.out.println("Usuario logueado asignado en initialize: " + usuarioLogueado);
            } else {
                System.out.println("No se encontró información del usuario logueado.");
            }
        }
    }

    @FXML
    public void btnSalirAc(ActionEvent event) {  // Metodo público
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Registro Cliente");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void btnAjustesAc(ActionEvent event) {  // Metodo público
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/ajustes.fxml", "Ajustes");
        } catch (Exception e) {
            Logger.getLogger(SolicitarCitaController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void btnVerCitasAc(ActionEvent event) {  // Metodo público
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verCitasCliente.fxml", "Ver citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void btnVerPerrosAc(ActionEvent event) {  // Metodo público
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Button getBtnSolicitarAdopcion() {
        return btnSolicitarAdopcion;
    }

    public void setBtnSolicitarAdopcion(Button btnSolicitarAdopcion) {
        this.btnSolicitarAdopcion = btnSolicitarAdopcion;
    }
}
