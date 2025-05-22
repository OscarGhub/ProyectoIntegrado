package controlador;

import Dao.RegistroProtectoraDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import modelo.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroProtectoraController implements Initializable {

    @FXML private Button btnConfitmar;
    @FXML private Button btnVolver;
    @FXML private TextField cajaCodigoPostal;
    @FXML private PasswordField cajaContrasenia;
    @FXML private TextField cajaCorreoElectronico;
    @FXML private TextField cajaLocalidad;
    @FXML private TextField cajaNombreVia;
    @FXML private TextField cajaPais;
    @FXML private TextField cajaProvincia;
    @FXML private TextField cajaTelefono;
    @FXML private TextField cajaTextUsuario;
    @FXML private ComboBox<String> cajaTipoVia;
    @FXML private ImageView imgUsuario;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            if (!validarCamposObligatorios()) return;

            if (!validarCorreo(cajaCorreoElectronico.getText())) {
                Alertas.mostrarAlertaAviso(null, "Error", "El correo electrónico no es válido.");
                return;
            }

            if (!validarTelefono(cajaTelefono.getText())) {
                Alertas.mostrarAlertaAviso(null, "Error", "El teléfono debe tener 9 dígitos.");
                return;
            }

            if (!validarCodigoPostal(cajaCodigoPostal.getText())) {
                Alertas.mostrarAlertaAviso(null, "Error", "El código postal debe tener 5 dígitos.");
                return;
            }

            if (cajaContrasenia.getText().length() < 6) {
                Alertas.mostrarAlertaAviso(null, "Error", "La contraseña debe tener al menos 6 caracteres.");
                return;
            }

            Protectora protectora = new Protectora();
            protectora.setNombreUsuario(cajaTextUsuario.getText());
            protectora.setContrasena(EncriptarContrasenia.encriptar(cajaContrasenia.getText()));
            protectora.setCorreoElectronico(cajaCorreoElectronico.getText());
            protectora.setTelefono(cajaTelefono.getText());
            protectora.setCodigoPostal(cajaCodigoPostal.getText());
            protectora.setLocalidad(cajaLocalidad.getText());
            protectora.setProvincia(cajaProvincia.getText());
            protectora.setPais(cajaPais.getText());

            TipoVia tipoVia = obtenerTipoViaDesdeTexto(cajaTipoVia.getValue());
            protectora.setTipoVia(tipoVia != null ? tipoVia.name() : "");
            protectora.setNombreVia(cajaNombreVia.getText());

            boolean registrado = RegistroProtectoraDAO.registrarProtectora(protectora);

            if (registrado) {
                UsuarioSesion.iniciarSesion(protectora);
                Alertas.mostrarAlertaAviso(null, "Éxito", "Protectora registrada correctamente.");
                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/modificarPerros.fxml", "Inicio");
            }

        } catch (Exception e) {
            Logger.getLogger(RegistroProtectoraController.class.getName()).log(Level.SEVERE, null, e);
            Alertas.mostrarAlertaError(null, "Error", "Ocurrió un error al registrar la protectora.");
        }
    }

    private boolean validarCamposObligatorios() {
        if (cajaTextUsuario.getText().isEmpty() ||
                cajaContrasenia.getText().isEmpty() ||
                cajaCorreoElectronico.getText().isEmpty() ||
                cajaTelefono.getText().isEmpty() ||
                cajaCodigoPostal.getText().isEmpty() ||
                cajaLocalidad.getText().isEmpty() ||
                cajaProvincia.getText().isEmpty() ||
                cajaPais.getText().isEmpty() ||
                cajaTipoVia.getValue() == null ||
                cajaNombreVia.getText().isEmpty()) {

            Alertas.mostrarAlertaError(null, "Error", "Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    private boolean validarCorreo(String correo) {
        String regexCorreo = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return correo.matches(regexCorreo);
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^[0-9]{9}$");
    }

    private boolean validarCodigoPostal(String cp) {
        return cp.matches("^[0-9]{5}$");
    }

    private TipoVia obtenerTipoViaDesdeTexto(String texto) {
        for (TipoVia tipo : TipoVia.values()) {
            String tipoFormateado = tipo.name().charAt(0) + tipo.name().substring(1).toLowerCase();
            if (tipoFormateado.equals(texto)) {
                return tipo;
            }
        }
        return null;
    }

    @FXML
    void btnVolverAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(RegistroProtectoraController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animaciones.animarImagenUsuario(imgUsuario);
        for (TipoVia tipo : TipoVia.values()) {
            cajaTipoVia.getItems().add(tipo.name().charAt(0) + tipo.name().substring(1).toLowerCase());
        }
    }
}
