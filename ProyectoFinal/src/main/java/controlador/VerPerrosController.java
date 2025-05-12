package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Ventanas;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerPerrosController implements Initializable {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarADP;

    @FXML
    private Button btnAjustes;

    @FXML
    private Button btnVerCitas;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private Button btnSolicitarCita;

    @FXML private TextField txtNombrePerro1;
    @FXML private TextField txtFechaNacimiento1;
    @FXML private TextField txtNombreRaza1;

    @FXML private TextField txtNombrePerro2;
    @FXML private TextField txtFechaNacimiento2;
    @FXML private TextField txtNombreRaza2;

    @FXML private TextField txtNombrePerro3;
    @FXML private TextField txtFechaNacimiento3;
    @FXML private TextField txtNombreRaza3;

    @FXML private TextField txtNombrePerro4;
    @FXML private TextField txtFechaNacimiento4;
    @FXML private TextField txtNombreRaza4;

    @FXML private TextField txtNombrePerro5;
    @FXML private TextField txtFechaNacimiento5;
    @FXML private TextField txtNombreRaza5;

    @FXML private TextField txtNombrePerro6;
    @FXML private TextField txtFechaNacimiento6;
    @FXML private TextField txtNombreRaza6;

    @FXML private TextField txtNombrePerro7;
    @FXML private TextField txtFechaNacimiento7;
    @FXML private TextField txtNombreRaza7;

    private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String usuario = "C##PROYECTOINTEGRADO";
    private final String contrasena = "123456";

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

        String sql = "SELECT p.nombre AS nombre_perro, p.fecha_nacimiento, r.nombre AS nombre_raza " +
                "FROM perro p JOIN raza r ON p.raza = r.id_raza";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int index = 0;
            while (rs.next() && index < campos.size()) {
                TextField[] tf = campos.get(index++);
                tf[0].setText(rs.getString("nombre_perro"));
                tf[1].setText(rs.getDate("fecha_nacimiento").toString());
                tf[2].setText(rs.getString("nombre_raza"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

        txtNombrePerro1.setEditable(false);
        txtFechaNacimiento1.setEditable(false);
        txtNombreRaza1.setEditable(false);

        txtNombrePerro2.setEditable(false);
        txtFechaNacimiento2.setEditable(false);
        txtNombreRaza2.setEditable(false);

        txtNombrePerro3.setEditable(false);
        txtFechaNacimiento3.setEditable(false);
        txtNombreRaza3.setEditable(false);

        txtNombrePerro4.setEditable(false);
        txtFechaNacimiento4.setEditable(false);
        txtNombreRaza4.setEditable(false);

        txtNombrePerro5.setEditable(false);
        txtFechaNacimiento5.setEditable(false);
        txtNombreRaza5.setEditable(false);

        txtNombrePerro6.setEditable(false);
        txtFechaNacimiento6.setEditable(false);
        txtNombreRaza6.setEditable(false);

        txtNombrePerro7.setEditable(false);
        txtFechaNacimiento7.setEditable(false);
        txtNombreRaza7.setEditable(false);

    }


}
