package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import modelo.EncriptarContrasenia;


public class inicioControlador {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnProtectora;



    @FXML
    void btnClienteAc(ActionEvent event) {
        // Mostrar alerta de confirmación antes de abrir la nueva ventana
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmación");


        // Botones personalizados
        ButtonType btnIniciarS = new ButtonType(" Iniciar Sesión ");
        ButtonType btnRegistrarse = new ButtonType(" Registrarse ");
        alert.getButtonTypes().setAll(btnIniciarS, btnRegistrarse);

        // Estilo personalizado
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        Optional<ButtonType> resultado = alert.showAndWait();

            try {
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/registroCliente.fxml"));
                Parent root = fxmlLoader.load();
                Scene escena = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Registro Cliente");
                stage.setScene(escena);
                stage.show();
            } catch (Exception e) {
                Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
            }
        }


    @FXML
    void btnProtectoraAc(ActionEvent event) {


        // Mostrar alerta de confirmación antes de abrir la nueva ventana
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmación");


        // Botones personalizados
        ButtonType btnIniciarS = new ButtonType(" Iniciar Sesión ");
        ButtonType btnRegistrarse = new ButtonType(" Registrarse ");
        alert.getButtonTypes().setAll(btnIniciarS, btnRegistrarse);

        // Estilo personalizado
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        Optional<ButtonType> resultado = alert.showAndWait();
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
            stage.show();

        } catch (Exception e) {
            Logger.getLogger(registroProtectoraController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
