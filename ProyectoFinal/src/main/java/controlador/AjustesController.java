package controlador;

import Dao.AjustesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import modelo.Alertas;
import modelo.Ventanas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjustesController implements Initializable {

    @FXML
    private Button btnSalir, btnSolicitarAdp, btnVerCitas, btnVerPerros;

    @FXML
    private ImageView imgTuerca1, imgTuerca2, imgTuerca3, imgUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
        modelo.Animaciones.animarRotacion(imgTuerca1);
        modelo.Animaciones.animarRotacion(imgTuerca2);
        modelo.Animaciones.animarRotacion(imgTuerca3);
    }

    @FXML
    void btnModContraseniaAc(ActionEvent event) {
        Optional<Pair<String, String>> resultado = crearAlertaUsuario("Contraseña");

        resultado.ifPresent(pair -> {
            String actual = pair.getKey();
            String nueva = pair.getValue();

            if (actual.isEmpty() || nueva.isEmpty()) {
                Alertas.mostrarAlertaError(null, "Error", "Los campos no pueden estar vacíos.");
                return;
            }

            if (AjustesDao.cambioContraseniaAjustes(actual, nueva)) {
                Alertas.mostrarAlertaConfirmacion(null, "Éxito", "Contraseña actualizada.");
            }
        });
    }

    @FXML
    void btnModGmailAc(ActionEvent event) {
        Optional<Pair<String, String>> resultado = crearAlertaUsuario("Correo electrónico");

        resultado.ifPresent(pair -> {
            String actual = pair.getKey();
            String nuevo = pair.getValue();

            if (actual.isEmpty() || nuevo.isEmpty()) {
                Alertas.mostrarAlertaError(null, "Error", "Los campos no pueden estar vacíos.");
                return;
            }

            if (AjustesDao.cambioCorreoAjustes(actual, nuevo)) {
                Alertas.mostrarAlertaConfirmacion(null, "Éxito", "Correo actualizado.");
            }
        });
    }

    @FXML
    void btnModUsarioAc(ActionEvent event) {
        Optional<Pair<String, String>> resultado = crearAlertaUsuario("Nombre de usuario");

        resultado.ifPresent(pair -> {
            String actual = pair.getKey();
            String nuevo = pair.getValue();

            if (actual.isEmpty() || nuevo.isEmpty()) {
                Alertas.mostrarAlertaError(null, "Error", "Los campos no pueden estar vacíos.");
                return;
            }

            if (AjustesDao.cambioUsuarioAjustes(actual, nuevo)) {
                Alertas.mostrarAlertaConfirmacion(null, "Éxito", "Nombre de usuario actualizado.");
            }
        });
    }

    @FXML
    void btnSalirAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/inicio.fxml", "Inicio");
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnSolicitarADPAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarAdopcion.fxml", "Solicitar adopción");
        } catch (Exception e) {
            Logger.getLogger(SolicitarAdpController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerCitasAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verCitasCliente.fxml", "Ver citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Método reutilizable para pedir datos de ajustes (usuario, correo, contraseña)
    public static Optional<Pair<String, String>> crearAlertaUsuario(String label) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ajustes");
        dialog.setHeaderText(null);

        ButtonType btnConfirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConfirmar, ButtonType.CANCEL);

        Label lblActual = new Label("Actual " + label);
        TextField txtActual = new TextField();

        Label lblNuevo = new Label("Nuevo " + label);
        TextField txtNuevo = new TextField();

        VBox vbox = new VBox(10);
        vbox.setPrefWidth(300);
        vbox.getChildren().addAll(lblActual, txtActual, lblNuevo, txtNuevo);

        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnConfirmar) {
                return new Pair<>(txtActual.getText(), txtNuevo.getText());
            }
            return null;
        });

        return dialog.showAndWait();
    }
}
