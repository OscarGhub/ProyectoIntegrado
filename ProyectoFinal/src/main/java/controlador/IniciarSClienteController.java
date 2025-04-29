package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.EncriptarContrasenia;

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

    private String contraseniaEncriptada="";

    private boolean sesionIniciada = false;


    @FXML
    void btnConfitmarAc(ActionEvent event) {
        try {
            String contrasenia = cajaTextContrasenia.getText();
            String usuario = cajaTextUsuario.getText();

            if (contrasenia == null || usuario == null) {
                throw new NullPointerException("Uno de los campos está vacío (null)");
            }

            contrasenia = contrasenia.trim();
            usuario = usuario.trim();

            if (usuario.equalsIgnoreCase("usuario")) {

                try {
                    // Cerrar la ventana actual
                    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verPerros.fxml"));
                    Parent root = fxmlLoader.load();
                    VerPerrosController controlador = fxmlLoader.getController();

                    // Crear la nueva escena
                    Scene escena = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Ver perros");
                    stage.setScene(escena);


                    if (sesionIniciada==false){
                        contraseniaEncriptada=EncriptarContrasenia.encriptarContraseia("cliente", contrasenia);
                        sesionIniciada = true;
                    }else {
                        if (EncriptarContrasenia.validarContrasenia(contrasenia,contraseniaEncriptada)==false){
                            mostrarAlerta("Error","Contraseña incorrecta",Alert.AlertType.WARNING);
                        }
                    }


                    // Mostrar la nueva ventana
                    stage.show();

                } catch (Exception e) {
                    Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
                    mostrarAlerta("Error", "No se pudo abrir la nueva ventana.", Alert.AlertType.ERROR);
                }

            } else {
                mostrarAlerta("Credenciales incorrectas", "Usuario o contraseña incorrectos.", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            mostrarAlerta("Campos vacíos", "Por favor, rellena todos los campos.", Alert.AlertType.ERROR);
        }
    }

    protected void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void btnVolverAc(ActionEvent event) {

        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/inicio.fxml"));
            Parent root = fxmlLoader.load();
            InicioControlador controlador = fxmlLoader.getController();

            // Crear la nueva escena
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
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
    }

}
