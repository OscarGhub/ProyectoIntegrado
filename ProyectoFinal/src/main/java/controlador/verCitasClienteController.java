package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class verCitasClienteController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Button btnSolicitarCitas;

    @FXML
    private Button btnVerCitas;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colPerro;

    @FXML
    private TableView<?> tablaCitas;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnSalirAc(ActionEvent event) {

        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/inicio.fxml"));
            Parent root = fxmlLoader.load();
            inicioControlador controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Inicio");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(inicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSolicitarAdpAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/solicitarAdopcion.fxml"));
            Parent root = fxmlLoader.load();
            solicitarAdpController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Solicitar adopción");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(solicitarAdpController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSolicitarCitasAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/solicitarCita.fxml"));
            Parent root = fxmlLoader.load();
            solicitarCitaController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Solicitar cita");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(solicitarCitaController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verPerros.fxml"));
            Parent root = fxmlLoader.load();
            verPerrosController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Ver perros");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void tablaCitasAc(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.animaciones.animarAgrandar(imgUsuario);
    }

}
