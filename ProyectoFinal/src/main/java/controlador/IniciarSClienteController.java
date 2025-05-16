package controlador;

import Dao.IniciarSesionClienteDao;
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
import modelo.Alertas;
import modelo.EncriptarContrasenia;
import modelo.Usuario;
import modelo.UsuarioSesion;
import modelo.Ventanas;
import modelo.Usuario;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IniciarSClienteController implements Initializable {

    @FXML
    private Button btnConfitmar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField cajaTextContrasenia;

    @FXML
    private TextField cajaTextUsuario;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            String correo = cajaTextUsuario.getText();
            String contraseniaPlana = cajaTextContrasenia.getText();

            if (correo.isEmpty() || contraseniaPlana.isEmpty()) {
                Alertas.mostrarAlertaError(null, "Error", "Usuario y contraseña son obligatorios");
                return;
            }

            String hashAlmacenado = IniciarSesionClienteDao.obtenerHashContrasenia(correo);

            if (hashAlmacenado == null) {
                Alertas.mostrarAlertaError(null, "Error", "Usuario no encontrado");
                return;
            }

            if (EncriptarContrasenia.verificar(contraseniaPlana, hashAlmacenado)) {
                UsuarioSesion.setCorreoElectronico(correo);

                // Obtener el usuario completo desde la base de datos
                Usuario usuarioCompleto = IniciarSesionClienteDao.obtenerUsuarioPorCorreo(correo);
                UsuarioSesion.setUsuarioActual(usuarioCompleto);

                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
            } else {
                Alertas.mostrarAlertaError(null, "Error", "Contraseña incorrecta");
            }

        } catch (Exception e) {
            Logger.getLogger(IniciarSClienteController.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(IniciarSClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void cajaTextContraseniaAc(ActionEvent event) {
        // vacío por ahora
    }

    @FXML
    void cajaTextUsuarioAc(ActionEvent event) {
        // vacío por ahora
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
    }

    public Button getBtnConfitmar() {
        return btnConfitmar;
    }

    public void setBtnConfitmar(Button btnConfitmar) {
        this.btnConfitmar = btnConfitmar;
    }

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }
}
