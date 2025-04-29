package controlador;

import utils.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);


        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        stage.setTitle("Dogpuccino");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        /*Conectar con la base de datos*/
        Connection con = ConnectionManager.getInstance().getConnection();
        launch();

    }
}
