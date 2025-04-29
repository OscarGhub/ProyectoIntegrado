package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/tabla.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        /*Cargar css en la escena */
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setTitle("Tabla");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
