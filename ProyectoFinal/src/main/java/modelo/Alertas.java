package modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;
import java.util.Optional;

import java.util.Optional;

public class Alertas {

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


        alert.showAndWait();
    }


    public static void mostrarAlertaError(Object claseActual, String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        DialogPane dialogPane = alert.getDialogPane();


        alert.showAndWait();
    }

    public static void mostrarAlertaConfirmacion(Object claseActual, String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        DialogPane dialogPane = alert.getDialogPane();


        alert.showAndWait();
    }


    public static Optional<Pair<String, String>> crearAlertaUsuario(Object claseActual,String label) {


        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ajustes");
        dialog.setHeaderText(null);


        ButtonType btnConfirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConfirmar, ButtonType.CANCEL);


        Label lblUsuario = new Label(label);
        TextField txtUsuario = new TextField();



        Label lblConfirmar = new Label("Confirmar "+label);
        TextField txtConfirmar = new TextField();


        VBox vbox = new VBox(10);
        vbox.setPrefWidth(300);
        vbox.getChildren().addAll(lblUsuario, txtUsuario, lblConfirmar, txtConfirmar);


        dialog.getDialogPane().setContent(vbox);



        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnConfirmar) {
                return new Pair<>(txtUsuario.getText(), txtConfirmar.getText());
            }
            return null;
        });

        return dialog.showAndWait();
    }

    public static void mostrarAlertaWarningGeneral(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    public static void mostrarConfirmacion(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

}
