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
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/modificarCitas.fxml"));
            Parent root = fxmlLoader.load();
            ModificarCitasController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Modificar citas");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(ModificarCitasController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSalirAc(ActionEvent event) {
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
        modelo.Animaciones.animarAgrandar(imgProtectora);
    }

}
