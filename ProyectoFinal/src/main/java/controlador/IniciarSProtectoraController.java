package controlador;

import Dao.IniciarSesionProtectoraDao;
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
import modelo.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IniciarSProtectoraController implements Initializable {

    @FXML
    private Button btnConfitmar;

    @FXML
    private Button btnVolver;

    @FXML
    private PasswordField cajaTextContrasenia;

    @FXML
    private TextField cajaTextUsuario;

    @FXML
    private ImageView imgProtectora;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            String usuario = cajaTextUsuario.getText();
            String contraseniaPlana = cajaTextContrasenia.getText();

            if (usuario.isEmpty() || contraseniaPlana.isEmpty()) {
                Alertas.mostrarAlertaError(null, "Error", "Usuario y contraseña son obligatorios");
                return;
            }

            String hashAlmacenado = IniciarSesionProtectoraDao.obtenerHashContraseniaP(usuario);

            if (hashAlmacenado == null) {
                Alertas.mostrarAlertaError(null, "Error", "Usuario no encontrado");
                return;
            }

            if (EncriptarContrasenia.verificar(contraseniaPlana, hashAlmacenado)) {
                // Obtener objeto Usuario completo
                Usuario usuarioCompleto = IniciarSesionProtectoraDao.obtenerUsuarioPorNombre(usuario);
                if (usuarioCompleto == null) {
                    Alertas.mostrarAlertaError(null, "Error", "No se pudo obtener la información del usuario.");
                    return;
                }

                UsuarioSesion.iniciarSesion(usuarioCompleto);

                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Ver perros");
            } else {
                Alertas.mostrarAlertaError(null, "Error", "Contraseña incorrecta");
            }

        } catch (Exception e) {
            Logger.getLogger(IniciarSProtectoraController.class.getName()).log(Level.SEVERE, null, e);
            Alertas.mostrarAlertaError(null, "Error", "Ocurrió un error al iniciar sesión");
        }
    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/inicio.fxml"));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Inicio");
            stage.setScene(escena);
            stage.show();

        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void cajaTextContraseniaAc(ActionEvent event) {
    }

    @FXML
    void cajaTextUsuarioAc(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgProtectora);
    }
}
