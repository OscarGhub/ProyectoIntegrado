package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitarCitaController implements Initializable {

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
    private ImageView imgUsuario;

    @FXML
    void btnEnviarAc(ActionEvent event) {

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
            Logger.getLogger(SolicitarAdpController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerCitasAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verCitasCliente.fxml", "Ver citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);
    }


}
