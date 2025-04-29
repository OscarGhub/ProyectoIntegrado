package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tablaController {

    @FXML
    private Button btnAgregar;

    @FXML
    private TableColumn<?, ?> colApellidos;

    @FXML
    private TableColumn<?, ?> colEdad;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableView<?> tabla;

    @FXML
    void btnAgregarAc(ActionEvent event)  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/formulario.fxml"));
            Parent root = fxmlLoader.load();
            formularioController controlador = (formularioController) fxmlLoader.getController();

            /*Creamos la escena*/
            Scene escena = new Scene(root);
            /*Creamos el escenario*/
            Stage stage = new Stage();
            /*Bloquea la ventada principal hasta que no se cierre la nueva*/
            stage.initModality(Modality.APPLICATION_MODAL);
            /*Se pone el titulo*/
            stage.setTitle("Formulario");
            /*Se asigna la escena a la ventana */
            stage.setScene(escena);
            /*Muestras la ventana y esperas a que el usuario la cierre antes de continuar*/
            stage.showAndWait();

        }catch (Exception e) {
            Logger.getLogger(tablaController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}

