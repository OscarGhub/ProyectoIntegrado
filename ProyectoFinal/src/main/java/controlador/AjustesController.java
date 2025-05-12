package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjustesController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarAdp;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnVerPerros;

    @FXML
    private ImageView imgTuerca1;

    @FXML
    private ImageView imgTuerca2;

    @FXML
    private ImageView imgTuerca3;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnModContraseniaAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Contraseña");
    }

    @FXML
    void btnModGmailAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Gmail");
    }

    @FXML
    void btnModUsarioAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Usuario");
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
    void btnSolicitarADPAc(ActionEvent event) {
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
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
        modelo.Animaciones.animarRotacion(imgTuerca1);
        modelo.Animaciones.animarRotacion(imgTuerca2);
        modelo.Animaciones.animarRotacion(imgTuerca3);
    }

}