package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.*;
import modelo.UsuarioSesion;

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
    private TextField cajaApellido;

    @FXML
    private PasswordField cajaContrasenia;

    @FXML
    private TextField cajaApellido2;

    @FXML
    private TextField cajaCodigoPostal;

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
    private DatePicker campoFecha;


    @FXML
    private ComboBox<String> comboTipoVia;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(cajaTextUsuario.getText());
            usuario.setApellido1(cajaApellido.getText());
            usuario.setApellido2(cajaApellido2.getText());
            usuario.setTelefono(cajaTelefono.getText());
            usuario.setCorreoElectronico(cajaCorreoElectronico.getText());
            usuario.setCodigoPostal(cajaCodigoPostal.getText());
            usuario.setLocalidad(cajaLocalidad.getText());
            usuario.setProvincia(cajaProvincia.getText());
            usuario.setPais(cajaPais.getText());
            usuario.setTipoVia(obtenerTipoViaDesdeTexto(comboTipoVia.getValue()).toString()); // <-- TIPO VIA
            usuario.setNombreVia(cajaNombreVia.getText());
            usuario.setNombreUsuario(cajaTextUsuario.getText());
            String contraseniaEncriptada = EncriptarContrasenia.encriptar(cajaContrasenia.getText());
            usuario.setContrasena(contraseniaEncriptada);
            usuario.setFechaNacimiento(campoFecha.getValue().toString());

            boolean registrado = Dao.RegistroClienteDAO.registrarClienteYUsuario(usuario);

            if (registrado) {
                UsuarioSesion.iniciarSesion(usuario);
                modelo.Alertas.mostrarAlertaAviso(null, "Éxito", "Registro completado correctamente.");
                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
            }
        } catch (Exception e) {
            Logger.getLogger(RegistroClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
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
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
        for (TipoVia tipo : TipoVia.values()) {
            comboTipoVia.getItems().add(tipo.name().charAt(0) + tipo.name().substring(1).toLowerCase());
        }
    }
}
