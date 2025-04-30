package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ajustesController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarAdp;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnVerPerros;

    @FXML
    private ImageView imgTuerca1;

    @FXML
    private ImageView imgTuerca2;

    @FXML
    private ImageView imgTuerca3;

    @FXML
    private ImageView imgUsuario;

    @FXML
    void btnModContraseniaAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Contraseña");
    }

    @FXML
    void btnModGmailAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Gmail");
    }

    @FXML
    void btnModUsarioAc(ActionEvent event) {
        modelo.Alertas.crearAlertaUsuario(event.getSource(),"Usuario");
    }

    @FXML
    void btnSalirAc(ActionEvent event) {

    }

    @FXML
    void btnSolicitarAdpAc(ActionEvent event) {

    }

    @FXML
    void btnVerCitasAc(ActionEvent event) {

    }

    @FXML
    void btnVerPerrosAc(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarImagenUsuario(imgUsuario);
        modelo.Animaciones.animarRotacion(imgTuerca1);
        modelo.Animaciones.animarRotacion(imgTuerca2);
        modelo.Animaciones.animarRotacion(imgTuerca3);
    }

}