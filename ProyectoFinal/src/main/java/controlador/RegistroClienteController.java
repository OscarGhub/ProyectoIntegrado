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
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verPerros.fxml"));
            Parent root = fxmlLoader.load();
            VerPerrosController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Inicio");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
    }


}
