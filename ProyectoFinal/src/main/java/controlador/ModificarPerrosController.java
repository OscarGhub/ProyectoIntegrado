package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import modelo.Ventanas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarPerrosController implements Initializable {

    @FXML
    private Button brnModificarCitas;

    @FXML
    private Button btnSalir;

    @FXML
    private ImageView imgProtectora;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgProtectora);
    }

}
