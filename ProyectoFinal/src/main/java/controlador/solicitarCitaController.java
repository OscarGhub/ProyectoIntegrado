package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class solicitarCitaController {

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarAdp;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnVerPerros;

    @FXML
    private TextField cajaTextApellido1;

    @FXML
    private TextField cajaTextApellido2;

    @FXML
    private TextField cajaTextCodigoP;

    @FXML
    private TextField cajaTextCorreoElect;

    @FXML
    private TextField cajaTextFechaNacimiento;

    @FXML
    private TextField cajaTextLocalidad;

    @FXML
    private TextField cajaTextNombre;

    @FXML
    private TextField cajaTextNvia;

    @FXML
    private TextField cajaTextPais;

    @FXML
    private TextField cajaTextProvincia;

    @FXML
    private TextField cajaTextTelef;

    @FXML
    private TextField cajaTextTipoVia;

    @FXML
    void btnEnviarAc(ActionEvent event) {

    }

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
            stage.setTitle("Registro Cliente");
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
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(solicitarAdpController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerCitasAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verCitasCliente.fxml"));
            Parent root = fxmlLoader.load();
            verCitasClienteController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(verCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
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
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
