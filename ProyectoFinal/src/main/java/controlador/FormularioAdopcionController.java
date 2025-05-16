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
    private Usuario usuarioLogueado;


    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
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
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos",
                    "Por favor, rellena todos los campos antes de guardar.");
            return;
        }

        try {
            String nombrePerro = txtNombrePerro.getText().trim();
            String correoCliente = txtCorreo.getText().trim();

            Connection conn = ConnectionManager.getInstance().getConnection();

            // Obtener el ID del perro
            String queryPerro = "SELECT perro_id FROM perro WHERE nombre = ?";
            int perroId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(queryPerro)) {
                stmt.setString(1, nombrePerro);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    perroId = rs.getInt("perro_id");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el perro en la base de datos.");
                    return;
                }
            }

            // Obtener el ID del cliente
            String queryCliente = "SELECT cliente_id FROM cliente WHERE correo_electronico = ?";
            int clienteId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(queryCliente)) {
                stmt.setString(1, correoCliente);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    clienteId = rs.getInt("cliente_id");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el cliente en la base de datos.");
                    return;
                }
            }

            // Insertar solicitud de adopción
            String insertSQL = "INSERT INTO solicitud_adopcion (perro_id, cliente_id, donacion, estado) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setInt(1, perroId);
                stmt.setInt(2, clienteId);
                stmt.setDouble(3, 0.0); // Puedes reemplazar esto si capturas donación
                stmt.setString(4, "pendiente"); // Estado por defecto

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

    public void setCorreoCliente(String correoCliente) {
        txtCorreo.setText(correoCliente);
        txtCorreo.setEditable(false);

        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT nombre, apellido1, apellido2 FROM cliente WHERE correo_electronico = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, correoCliente);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");

                    System.out.println("Cliente encontrado: " + nombre + " " + apellido1 + " " + apellido2);

                    txtNombre.setText(nombre);
                    txtApellido1.setText(apellido1);
                    txtApellido2.setText(apellido2);

                    txtNombre.setEditable(false);
                    txtApellido1.setEditable(false);
                    txtApellido2.setEditable(false);
                } else {
                    mostrarAlerta(Alert.AlertType.WARNING, "Cliente no encontrado",
                            "No se encontró un cliente con ese correo en la base de datos.");
                }
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al recuperar datos del cliente",
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
