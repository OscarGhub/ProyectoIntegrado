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
    private TextField cajaApellido;

    @FXML
    private TextField cajaApellido2;

    @FXML
    private TextField cajaCodigoPostal;

    @FXML
    private TextField cajaCorreoElectronico;

    @FXML
    private TextField cajaFechaNacimiento;

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
    private TextField cajaTipoVia;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            if (modelo.SesionInciada.iniciarSesion()) {
                modelo.Alertas.mostrarAlertaAviso(this, "Error", "Usted ya está registrado en la aplicación.");
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(cajaTextUsuario.getText());
            usuario.setApellido1(cajaApellido.getText());
            usuario.setApellido2(cajaApellido2.getText());
            usuario.setFechaNacimiento(cajaFechaNacimiento.getText()); // formato YYYY-MM-DD
            usuario.setTelefono(cajaTelefono.getText());
            usuario.setCorreoElectronico(cajaCorreoElectronico.getText());
            usuario.setCodigoPostal(cajaCodigoPostal.getText());
            usuario.setLocalidad(cajaLocalidad.getText());
            usuario.setProvincia(cajaProvincia.getText());
            usuario.setPais(cajaPais.getText());
            usuario.setTipoVia(cajaTipoVia.getText());
            usuario.setNombreVia(cajaNombreVia.getText());

            usuario.setNombreUsuario(cajaTextUsuario.getText()); // mismo campo que nombre
            usuario.setContrasena(""); // Aquí deberías enlazar un PasswordField y obtener la contraseña real

            boolean registrado = Dao.RegistroClienteDAO.registrarClienteYUsuario(usuario);

            if (registrado) {
                modelo.Alertas.mostrarAlertaAviso(null, "Éxito", "Registro completado correctamente.");
                Ventanas.cerrarVentana(event);
                Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
            }
        } catch (Exception e) {
            Logger.getLogger(RegistroClienteController.class.getName()).log(Level.SEVERE, null, e);
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
