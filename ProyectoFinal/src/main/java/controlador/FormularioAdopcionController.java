package controlador;

import Dao.FormularioAdopcionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import modelo.Usuario;

import utils.ConnectionManager;

public class FormularioAdopcionController {

    // Campos del formulario
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido1;
    @FXML private TextField txtApellido2;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtNombrePerro;
    @FXML private TextField txtRazaPerro;
    @FXML private TextField txtSexoPerro;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;


    public void setUsuarioLogueado(Usuario usuario) {
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido1.setText(usuario.getApellido1());
            txtApellido2.setText(usuario.getApellido2());
            txtCorreo.setText(usuario.getCorreoElectronico());

            // Hacer campos no editables
            txtNombre.setEditable(false);
            txtApellido1.setEditable(false);
            txtApellido2.setEditable(false);
            txtCorreo.setEditable(false);
        }
    }

    @FXML
    public void guardarSolicitud(ActionEvent event) {
        if (camposVacios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor, rellena todos los campos.");
            return;
        }

        try {
            String nombrePerro = txtNombrePerro.getText().trim();
            String correo = txtCorreo.getText().trim();

            int perroId = FormularioAdopcionDAO.obtenerIdPerro(nombrePerro);
            if (perroId == -1) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el perro.");
                return;
            }

            int clienteId = FormularioAdopcionDAO.obtenerIdCliente(correo);
            if (clienteId == -1) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el cliente.");
                return;
            }

            boolean ok = FormularioAdopcionDAO.insertarSolicitudAdopcion(perroId, clienteId, 0.0);
            if (ok) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Solicitud enviada", "Se ha registrado con éxito.");
                cerrarVentana();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar la solicitud.");
            }

        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de base de datos", e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean camposVacios() {
        return txtNombre.getText().isEmpty() ||
                txtApellido1.getText().isEmpty() ||
                txtApellido2.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() ||
                txtNombrePerro.getText().isEmpty() ||
                txtRazaPerro.getText().isEmpty() ||
                txtSexoPerro.getText().isEmpty();
    }

    public void setNombrePerro(String nombrePerro) {
        txtNombrePerro.setText(nombrePerro);
        txtNombrePerro.setEditable(false);

        try {
            String[] datos = FormularioAdopcionDAO.obtenerInfoPerro(nombrePerro);
            if (datos != null) {
                txtRazaPerro.setText(datos[0]);
                txtSexoPerro.setText(datos[1]);
                txtRazaPerro.setEditable(false);
                txtSexoPerro.setEditable(false);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No se encontró ese perro.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de base de datos", e.getMessage());
            e.printStackTrace();
        }
    }

    public void setCorreoCliente(String correo) {
        txtCorreo.setText(correo);
        txtCorreo.setEditable(false);

        try {
            String[] datos = FormularioAdopcionDAO.obtenerInfoCliente(correo);
            if (datos != null) {
                txtNombre.setText(datos[0]);
                txtApellido1.setText(datos[1]);
                txtApellido2.setText(datos[2]);

                txtNombre.setEditable(false);
                txtApellido1.setEditable(false);
                txtApellido2.setEditable(false);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No se encontró ese cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de base de datos", e.getMessage());
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void salirFormulario(ActionEvent event) {
        cerrarVentana();
    }
}
