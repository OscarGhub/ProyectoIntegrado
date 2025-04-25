package modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;

public class alertas {

    public static Optional<ButtonType> crearAlertaEleccion(Object claseActual) {

        Alert alert = new Alert(Alert.AlertType.NONE);


        ButtonType btnIniciarS = new ButtonType("→ Iniciar Sesión ");
        ButtonType btnRegistrarse = new ButtonType("→ Registrarse");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnIniciarS, btnRegistrarse);


        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(claseActual.getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        return alert.showAndWait();
    }

    public static void mostrarAlertaAviso(Object claseActual, String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(claseActual.getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        alert.showAndWait();
    }


    public static void mostrarAlertaError(Object claseActual, String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(claseActual.getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        alert.showAndWait();
    }

    public static void mostrarAlertaConfirmacion(Object claseActual, String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(claseActual.getClass().getResource("/EstilosEleccion.css").toExternalForm());
        dialogPane.getStyleClass().add("mi-alerta-personalizada");

        alert.showAndWait();
    }
}
