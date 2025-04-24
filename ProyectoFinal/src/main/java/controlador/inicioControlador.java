package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
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
        // Crear alerta con tipo de confirmación
        Alert alert = new Alert(null);


        // Botones personalizados
        ButtonType btnIniciarS = new ButtonType("Iniciar Sesión");
        ButtonType btnRegistrarse = new ButtonType("Registrarse");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnIniciarS, btnRegistrarse );

        // Estilo personalizado
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent()) {
            try {
                if (resultado.get() == btnIniciarS) {
                    // Acción para Iniciar Sesión
                    abrirVentana("/vista/iniciarSesionCliente.fxml", "Iniciar Sesión", event);
                } else if (resultado.get() == btnRegistrarse) {
                    // Acción para Registrarse
                    abrirVentana("/vista/registroCliente.fxml", "Registro Cliente", event);
                }
                // Si es cancelar o se cierra con la X, no hace nada
            } catch (Exception e) {
                Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Método para abrir ventanas
    private void abrirVentana(String rutaFXML, String titulo, ActionEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = fxmlLoader.load();
        Scene escena = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(escena);
        stage.show();
    }



    @FXML
    void btnProtectoraAc(ActionEvent event) {


        // Crear alerta con tipo de confirmación
        Alert alert = new Alert(null);


        // Botones personalizados
        ButtonType btnIniciarS = new ButtonType("Iniciar Sesión");
        ButtonType btnRegistrarse = new ButtonType("Registrarse");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnIniciarS, btnRegistrarse );

        // Estilo personalizado
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent()) {
            try {
                if (resultado.get() == btnIniciarS) {
                    // Acción para Iniciar Sesión
                    abrirVentana("/vista/iniciarSesionProtectora.fxml", "Iniciar Sesión", event);
                } else if (resultado.get() == btnRegistrarse) {
                    // Acción para Registrarse
                    abrirVentana("/vista/registroProtectora.fxml", "Registro Cliente", event);
                }
                // Si es cancelar o se cierra con la X, no hace nada
            } catch (Exception e) {
                Logger.getLogger(verPerrosController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
