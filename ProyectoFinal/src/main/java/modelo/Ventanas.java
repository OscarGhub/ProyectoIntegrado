package modelo;

import controlador.IniciarSClienteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ventanas {

    /**
     * Método para mostrar una alerta personalizada.
     * @param rutaFXML La ruta del archivo FXML de la ventana a abrir.
     */

    private void abrirVentana(String rutaFXML, ActionEvent event) {
        try {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena);
            stage.show();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "No se pudo abrir la ventana: " + rutaFXML, e);
        }
    }

}
