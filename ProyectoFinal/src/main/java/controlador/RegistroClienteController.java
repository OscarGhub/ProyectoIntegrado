package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Usuario;
import modelo.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroClienteController implements Initializable {

    @FXML
    private Button btnConfitmar;

    @FXML
    private Button btnVolver;

    @FXML
    private PasswordField cajaTextContrasenia;

    @FXML
    private PasswordField cajaTextContraseniaConfirmar;

    @FXML
    private TextField cajaTextGmail;

    @FXML
    private TextField cajaTextUsuario;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            if (modelo.SesionInciada.iniciarSesion() == true) {
                modelo.Alertas.mostrarAlertaAviso(this, "Error", "Usted ya está registrado en la aplicación.");
            } else {

                Usuario usuario = new Usuario();

                String usuarioname = cajaTextUsuario.getText();
                String gmail = cajaTextGmail.getText();
                String contrasenia = cajaTextContrasenia.getText();
                String confirmarContrasenia = cajaTextContraseniaConfirmar.getText();

                usuario.setUsername(usuarioname);
                usuario.setPassword(contrasenia);
                usuario.setGmail(gmail);

                if (contrasenia.equals(confirmarContrasenia)) {
                    modelo.SesionInciada.iniciarSesion(usuario);
                } else {
                    modelo.Alertas.mostrarAlertaError(this, "Contraseña Incorrecta", "Error al ingresar los datos.");
                }

                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
            }
        } catch (Exception e) {
            Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
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
    }
}
