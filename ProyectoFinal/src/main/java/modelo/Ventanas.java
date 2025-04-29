package modelo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventanas {

    public static void cerrarVentana(javafx.event.ActionEvent event) {
        if (event != null && event.getSource() instanceof javafx.scene.Node) {
            javafx.scene.Node source = (javafx.scene.Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
        }
    }

    public static void abrirVentana(String rutaFXML, String titulo) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Ventanas.class.getResource(rutaFXML));
        Parent root = fxmlLoader.load();

        Scene escena = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(escena);
        stage.show();
    }
}
