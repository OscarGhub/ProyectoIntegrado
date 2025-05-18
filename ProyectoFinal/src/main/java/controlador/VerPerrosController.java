package controlador;

import Dao.VerPerrosDAO;
import Dao.VerPerrosDAO.PerroDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Ventanas;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;

public class VerPerrosController implements Initializable {

    @FXML private Button btnSalir;
    @FXML private Button btnSolicitarADP;
    @FXML private Button btnAjustes;
    @FXML private Button btnVerCitas;
    @FXML private ImageView imgUsuario;
    @FXML private Button btnSolicitarCita;

    @FXML private TextField txtNombrePerro1, txtFechaNacimiento1, txtNombreRaza1;
    @FXML private TextField txtNombrePerro2, txtFechaNacimiento2, txtNombreRaza2;
    @FXML private TextField txtNombrePerro3, txtFechaNacimiento3, txtNombreRaza3;
    @FXML private TextField txtNombrePerro4, txtFechaNacimiento4, txtNombreRaza4;
    @FXML private TextField txtNombrePerro5, txtFechaNacimiento5, txtNombreRaza5;
    @FXML private TextField txtNombrePerro6, txtFechaNacimiento6, txtNombreRaza6;
    @FXML private TextField txtNombrePerro7, txtFechaNacimiento7, txtNombreRaza7;

    private int clienteIdActual = 1;

    private void cargarDatosPerros() {
        List<TextField[]> campos = List.of(
                new TextField[]{txtNombrePerro1, txtFechaNacimiento1, txtNombreRaza1},
                new TextField[]{txtNombrePerro2, txtFechaNacimiento2, txtNombreRaza2},
                new TextField[]{txtNombrePerro3, txtFechaNacimiento3, txtNombreRaza3},
                new TextField[]{txtNombrePerro4, txtFechaNacimiento4, txtNombreRaza4},
                new TextField[]{txtNombrePerro5, txtFechaNacimiento5, txtNombreRaza5},
                new TextField[]{txtNombrePerro6, txtFechaNacimiento6, txtNombreRaza6},
                new TextField[]{txtNombrePerro7, txtFechaNacimiento7, txtNombreRaza7}
        );

        VerPerrosDAO dao = new VerPerrosDAO();
        List<PerroDTO> perros = dao.obtenerPerros();

        for (int i = 0; i < perros.size() && i < campos.size(); i++) {
            PerroDTO perro = perros.get(i);
            TextField[] tf = campos.get(i);
            tf[0].setText(perro.getNombre());
            tf[1].setText(perro.getFechaNacimiento().toString());
            tf[2].setText(perro.getRaza());
        }
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
    void btnAjustesAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/ajustes.fxml", "Ajustes");
        } catch (Exception e) {
            Logger.getLogger(SolicitarCitaController.class.getName()).log(Level.SEVERE, null, e);
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
    void btnSolicitarCitasAc(ActionEvent event) {
        try {
            Ventanas.abrirVentana("/vista/solicitarCita.fxml", "Solicitar citas");
        } catch (Exception e) {
            Logger.getLogger(VerCitasClienteController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);
        cargarDatosPerros();
        verificarNotificaciones();

        // Hacer los campos no editables
        List<TextField> todosLosCampos = List.of(
                txtNombrePerro1, txtFechaNacimiento1, txtNombreRaza1,
                txtNombrePerro2, txtFechaNacimiento2, txtNombreRaza2,
                txtNombrePerro3, txtFechaNacimiento3, txtNombreRaza3,
                txtNombrePerro4, txtFechaNacimiento4, txtNombreRaza4,
                txtNombrePerro5, txtFechaNacimiento5, txtNombreRaza5,
                txtNombrePerro6, txtFechaNacimiento6, txtNombreRaza6,
                txtNombrePerro7, txtFechaNacimiento7, txtNombreRaza7
        );

        for (TextField tf : todosLosCampos) {
            tf.setEditable(false);
        }
    }

    private void verificarNotificaciones() {
        VerPerrosDAO dao = new VerPerrosDAO();
        boolean tieneNotificaciones = dao.tieneNotificaciones(clienteIdActual);

        try {
            if (tieneNotificaciones) {
                // Cambiar a imagen con notificación
                Image imagenNotificacion = new Image(getClass().getResourceAsStream("/img/usuarioNotificacion.png"));
                imgUsuario.setImage(imagenNotificacion);
            } else {
                // Mantener imagen normal
                Image imagenNormal = new Image(getClass().getResourceAsStream("/img/login-removebg-preview.png"));
                imgUsuario.setImage(imagenNormal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mirarNotificaciones(MouseEvent event) {
        VerPerrosDAO dao = new VerPerrosDAO();
        boolean tieneNotificaciones = dao.tieneNotificaciones(clienteIdActual);

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Notificación");

        Label mensaje = new Label(tieneNotificaciones ?
                "¡Tienes notificaciones nuevas!" : "No tienes notificaciones nuevas");

        VBox layout = new VBox(10, mensaje);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Si hay notificaciones, añadir botón para marcarlas como leídas
        if (tieneNotificaciones) {
            Button btnMarcarLeidas = new Button("Marcar como leídas");
            btnMarcarLeidas.setOnAction(e -> {
                // Aquí podrías implementar la lógica para marcar notificaciones como leídas
                // Por ahora solo cambiamos la imagen
                Image imagenNormal = new Image(getClass().getResourceAsStream("/img/login-removebg-preview.png"));
                imgUsuario.setImage(imagenNormal);
                popup.close();
            });
            layout.getChildren().add(btnMarcarLeidas);
        }

        popup.setScene(new Scene(layout, 300, 150));
        popup.showAndWait();
    }
}
