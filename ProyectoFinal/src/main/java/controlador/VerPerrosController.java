package controlador;

import Dao.VerPerrosDAO;
import Dao.VerPerrosDAO.PerroDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.UsuarioSesion;
import modelo.Ventanas;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/solicitarAdopcion.fxml"));
            Parent root = loader.load();

            SolicitarAdpController controller = loader.getController();

            controller.setUsuarioLogueado(UsuarioSesion.getUsuario());

            Stage stage = new Stage();
            stage.setTitle("Solicitar adopción");
            stage.setScene(new Scene(root));
            stage.show();

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

    public Button getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(Button btnSalir) {
        this.btnSalir = btnSalir;
    }

    public Button getBtnSolicitarADP() {
        return btnSolicitarADP;
    }

    public void setBtnSolicitarADP(Button btnSolicitarADP) {
        this.btnSolicitarADP = btnSolicitarADP;
    }

    public Button getBtnAjustes() {
        return btnAjustes;
    }

    public void setBtnAjustes(Button btnAjustes) {
        this.btnAjustes = btnAjustes;
    }

    public Button getBtnVerCitas() {
        return btnVerCitas;
    }

    public void setBtnVerCitas(Button btnVerCitas) {
        this.btnVerCitas = btnVerCitas;
    }

    public Button getBtnSolicitarCita() {
        return btnSolicitarCita;
    }

    public void setBtnSolicitarCita(Button btnSolicitarCita) {
        this.btnSolicitarCita = btnSolicitarCita;
    }
}
