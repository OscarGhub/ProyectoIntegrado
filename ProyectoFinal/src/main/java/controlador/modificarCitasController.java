package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarCitasController {

    @FXML
    private Button brnModificarPerros;

    @FXML
    private Button btnSalir;

    @FXML
    void brnModificarPerrosAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/modificarPerros.fxml"));
            Parent root = fxmlLoader.load();
            modificarPerrosController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(modificarPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSalirAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/inicio.fxml"));
            Parent root = fxmlLoader.load();
            inicioControlador controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);


            stage.show();

        } catch (Exception e) {
            Logger.getLogger(inicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
