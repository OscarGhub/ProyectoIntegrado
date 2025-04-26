package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerPerrosController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarADP;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Button btnVerCitas;

    @FXML
    private ImageView imgUsuario;

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
    void btnSolicitarCitaAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarCita.fxml", "Solicitar cita");
        } catch (Exception e) {
            Logger.getLogger(SolicitarCitaController.class.getName()).log(Level.SEVERE, null, e);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);
    }

}
