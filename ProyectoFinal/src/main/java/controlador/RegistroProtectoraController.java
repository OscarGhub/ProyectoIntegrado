package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.TipoVia;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroProtectoraController implements Initializable {

    @FXML
    private Button btnConfitmar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField cajaCodigoPostal;

    @FXML
    private PasswordField cajaContrasenia;

    @FXML
    private TextField cajaCorreoElectronico;

    @FXML
    private TextField cajaLocalidad;

    @FXML
    private TextField cajaNombreVia;

    @FXML
    private TextField cajaPais;

    @FXML
    private TextField cajaProvincia;

    @FXML
    private TextField cajaTelefono;

    @FXML
    private TextField cajaTextUsuario;

    @FXML
    private ComboBox<String> cajaTipoVia;

    @FXML
    private ImageView imgUsuario;


    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            modelo.Protectora protectora = new modelo.Protectora();
            protectora.setNombreUsuario(cajaTextUsuario.getText());
            protectora.setContrasena(cajaContrasenia.getText());
            protectora.setCorreoElectronico(cajaCorreoElectronico.getText());
            protectora.setTelefono(cajaTelefono.getText());
            protectora.setCodigoPostal(cajaCodigoPostal.getText());
            protectora.setLocalidad(cajaLocalidad.getText());
            protectora.setProvincia(cajaProvincia.getText());
            protectora.setPais(cajaPais.getText());
            protectora.setTipoVia(cajaTipoVia.getValue());
            protectora.setNombreVia(cajaNombreVia.getText());

            boolean registrado = Dao.RegistroProtectoraDAO.registrarProtectora(protectora);

            if (registrado) {
                modelo.Alertas.mostrarAlertaAviso(null, "Éxito", "Protectora registrada correctamente.");
                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Inicio");
            }

        } catch (Exception e) {
            Logger.getLogger(RegistroProtectoraController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
        for (TipoVia tipo : TipoVia.values()) {
            cajaTipoVia.getItems().add(tipo.name().charAt(0) + tipo.name().substring(1).toLowerCase());
        }
    }

}
