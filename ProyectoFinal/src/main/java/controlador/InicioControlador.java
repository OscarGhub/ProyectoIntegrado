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


public class InicioControlador {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnProtectora;



    @FXML
    void btnClienteAc(ActionEvent event) {

        Optional<ButtonType> resultado =modelo.Alertas.crearAlertaEleccion(this);

        if (resultado.isPresent()) {
            try {
                if (resultado.get().getText().contains("Iniciar")) {
                    abrirVentana("/vista/iniciarSesionCliente.fxml", "Iniciar Sesión", event);
                } else if (resultado.get().getText().contains("Registrarse")) {
                    abrirVentana("/vista/registroCliente.fxml", "Registro Cliente", event);
                }
            } catch (Exception e) {
                Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
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

        Optional<ButtonType> resultado =modelo.Alertas.crearAlertaEleccion(this);

        if (resultado.isPresent()) {
            try {
                if (resultado.get().getText().contains("Iniciar")) {
                    abrirVentana("/vista/iniciarSesionProtectora.fxml", "Iniciar Sesión", event);
                } else if (resultado.get().getText().contains("Registrarse")) {
                    abrirVentana("/vista/registroProtectora.fxml", "Registro Cliente", event);
                }
            } catch (Exception e) {
                Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public Button getBtnCliente() {
        return btnCliente;
    }

    public void setBtnCliente(Button btnCliente) {
        this.btnCliente = btnCliente;
    }

    public Button getBtnProtectora() {
        return btnProtectora;
    }

    public void setBtnProtectora(Button btnProtectora) {
        this.btnProtectora = btnProtectora;
    }
}
