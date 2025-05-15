package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

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


    @FXML
    public void guardarSolicitud(ActionEvent event) {
        if (camposVacios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos",
                    "Por favor, rellena todos los campos antes de guardar.");
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            String apellido1 = txtApellido1.getText().trim();
            String apellido2 = txtApellido2.getText().trim();
            String correo = txtCorreo.getText().trim();
            String nombre_perro = txtNombrePerro.getText().trim();
            String raza_perro = txtRazaPerro.getText().trim();


            Connection conn = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO solicitud_adopcion (nombre, apellido1, apellido2, correo, nombre_perro, raza_perro) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido1);
                stmt.setString(3, apellido2);
                stmt.setString(4, correo);
                stmt.setString(5, nombre_perro);
                stmt.setString(6, raza_perro);


                int filas = stmt.executeUpdate();

                if (filas > 0) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Solicitud enviada",
                            "La solicitud de adopción se ha registrado con éxito.");
                    cerrarVentana();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar la solicitud.");
                }
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error: " + e.getMessage());
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
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT r.nombre AS nombre_raza, p.\"SEXO\" " +  // Ajusta a p.sexo si el campo no está entre comillas
                    "FROM perro p " +
                    "JOIN raza r ON p.raza = r.id_raza " +
                    "WHERE p.nombre = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombrePerro);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    String nombreRaza = rs.getString("nombre_raza");
                    String sexo = rs.getString(2); // o rs.getString("SEXO") si Oracle devuelve el nombre así

                    txtRazaPerro.setText(nombreRaza);
                    txtRazaPerro.setEditable(false);
                    txtSexoPerro.setText(sexo);
                    txtSexoPerro.setEditable(false);
                } else {
                    mostrarAlerta(Alert.AlertType.WARNING, "Perro no encontrado",
                            "No se encontró un perro con ese nombre en la base de datos.");
                }
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al recuperar datos",
                    "Ocurrió un error al consultar la base de datos: " + e.getMessage());
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
