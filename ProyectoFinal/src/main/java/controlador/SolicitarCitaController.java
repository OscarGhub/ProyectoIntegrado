package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Formulario;
import modelo.TipoVia;
import modelo.Ventanas;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitarCitaController implements Initializable {

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSolicitarAdp;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnVerPerros;

    @FXML
    private TextField cajaTextApellido1;

    @FXML
    private TextField cajaTextApellido2;

    @FXML
    private TextField cajaTextCodigoP;

    @FXML
    private TextField cajaTextCorreoElect;

    @FXML
    private DatePicker datePickerFechaNacimiento;

    @FXML
    private TextField cajaTextLocalidad;

    @FXML
    private TextField cajaTextNombre;

    @FXML
    private TextField cajaTextNvia;

    @FXML
    private TextField cajaTextPais;

    @FXML
    private TextField cajaTextProvincia;

    @FXML
    private TextField cajaTextTelef;

    @FXML
    private ChoiceBox<TipoVia> choiceBoxTipoVia;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private DatePicker datePickerFechaCita;

    // Método que se ejecuta cuando el usuario hace clic en "Enviar"
    @FXML
    void btnEnviarAc(ActionEvent event) {
        // Recoger los valores de los campos de texto
        String nombre = cajaTextNombre.getText();
        String apellido1 = cajaTextApellido1.getText();
        String apellido2 = cajaTextApellido2.getText();
        String fechaNacimiento = datePickerFechaNacimiento.toString();
        String telefono = cajaTextTelef.getText();
        String correo = cajaTextCorreoElect.getText();
        String codigoPostal = cajaTextCodigoP.getText();
        String localidad = cajaTextLocalidad.getText();
        String provincia = cajaTextProvincia.getText();
        String pais = cajaTextPais.getText();
        String tipoViaString = choiceBoxTipoVia.toString();
        String numVia = cajaTextNvia.getText();
        String fechaCita = datePickerFechaCita.getValue().toString();

        // Crear el objeto Formulario
        Formulario formulario = new Formulario();
        formulario.setNombre(nombre);
        formulario.setApellido1(apellido1);
        formulario.setApellido2(apellido2);
        formulario.setFecha_nacimiento(fechaNacimiento);
        formulario.setTelefono(telefono);
        formulario.setCorreo(correo);
        formulario.setCodigo_postal(codigoPostal);
        formulario.setLocalidad(localidad);
        formulario.setProvincia(provincia);
        formulario.setPais(pais);
        formulario.setTipoVia(TipoVia.valueOf(tipoViaString.toUpperCase()));
        formulario.setNum_via(numVia);
        formulario.setFechaCita(fechaCita);

        guardarEnBaseDeDatos(formulario);
    }

    private void guardarEnBaseDeDatos(Formulario formulario) {
        String url = "db.url=jdbc:oracle:thin:@localhost:1521:XE";
        String usuario = "C##PROYECTOINTEGRADO";
        String contrasena = "123456";

        String sql = "INSERT INTO formularios (nombre, apellido1, apellido2, fecha_nacimiento, telefono, correo, codigo_postal, localidad, provincia, pais, tipo_via, num_via) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, formulario.getNombre());
            preparedStatement.setString(2, formulario.getApellido1());
            preparedStatement.setString(3, formulario.getApellido2());
            preparedStatement.setString(4, formulario.getFecha_nacimiento());
            preparedStatement.setString(5, formulario.getTelefono());
            preparedStatement.setString(6, formulario.getCorreo());
            preparedStatement.setString(7, formulario.getCodigo_postal());
            preparedStatement.setString(8, formulario.getLocalidad());
            preparedStatement.setString(9, formulario.getProvincia());
            preparedStatement.setString(10, formulario.getPais());
            preparedStatement.setString(11, formulario.getTipoVia().name());
            preparedStatement.setString(12, formulario.getNum_via());
            preparedStatement.setString(13, formulario.getFechaCita());

            preparedStatement.executeUpdate();

            System.out.println("Datos insertados correctamente en la base de datos");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar los datos en la base de datos");
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
    void btnSolicitarAdpAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/solicitarAdopcion.fxml", "Solicitar adopción");
        } catch (Exception e) {
            Logger.getLogger(SolicitarAdpController.class.getName()).log(Level.SEVERE, null, e);
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
    void btnVerPerrosAc(ActionEvent event) {
        try {
            Ventanas.cerrarVentana(event);
            Ventanas.abrirVentana("/vista/verPerros.fxml", "Ver perros");
        } catch (Exception e) {
            Logger.getLogger(VerPerrosController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelo.Animaciones.animarAgrandar(imgUsuario);
        choiceBoxTipoVia.getItems().addAll(TipoVia.values());
    }

}
