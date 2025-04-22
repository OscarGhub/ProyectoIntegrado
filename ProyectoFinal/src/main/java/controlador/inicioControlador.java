package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;


public class inicioControlador {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnProtectora;

    @FXML
    void btnClienteAc(ActionEvent event) {


        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/registroCliente.fxml"));
            Parent root = fxmlLoader.load();
            registroClienteController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Cliente");
            stage.setScene(escena);

            // Mostrar la nueva ventana y esperar a que se cierre
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnProtectoraAc(ActionEvent event) {
        try {
            // Cerrar la ventana actual
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/registroProtectora.fxml"));
            Parent root = fxmlLoader.load();
            registroProtectoraController controlador = fxmlLoader.getController();

            // Crear la nueva escena
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Protectora");
            stage.setScene(escena);

            // Mostrar la nueva ventana y esperar a que se cierre
            stage.showAndWait();

        } catch (Exception e) {
            Logger.getLogger(registroProtectoraController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
