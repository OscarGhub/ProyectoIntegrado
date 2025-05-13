package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.CitasInfo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarCitaClienteController {

    private java.time.LocalDate fechaCitaOriginal;
    private String horaCitaOriginal;

    @FXML
    private DatePicker fechaCita; // Fecha de la cita
    @FXML
    private TextField horaCita; // Hora de la cita

    private CitasInfo citaSeleccionada;

    // Este método se llama desde el controlador principal
    public void setCitaSeleccionada(CitasInfo cita) {
        this.citaSeleccionada = cita;
        this.fechaCitaOriginal = cita.getFechaCita();
        this.horaCitaOriginal = cita.getHoraCita();

        fechaCita.setValue(cita.getFechaCita());
        horaCita.setText(cita.getHoraCita());
    }

    // Método para guardar los cambios de la cita
    @FXML
    private void guardarCambios() {
        if (fechaCita.getValue() == null || horaCita.getText().isEmpty()) {

            // Si hay campos vacíos, mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos Vacíos");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
        } else {
            // Si todo está bien, actualizar la cita (solo fecha y hora)
            citaSeleccionada.setFechaCita(fechaCita.getValue());
            citaSeleccionada.setHoraCita(horaCita.getText());

            // Verificamos el estado y si no es "Pendiente", lo cambiamos a "Pendiente"
            if (!"Pendiente".equals(citaSeleccionada.getEstado())) {
                citaSeleccionada.setEstado("Pendiente");
            }

            // Llamar al método que guarda la cita en la base de datos
            guardarCambiosCita(citaSeleccionada);
        }
    }

    // Método para actualizar la cita en la base de datos
    private void guardarCambiosCita(CitasInfo cita) {
        // Datos de la conexión
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##PROYECTOINTEGRADO";
        String password = "123456";

        // Consulta SQL para actualizar la cita, incluyendo el estado
        String query = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                "WHERE cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?) " +
                "AND perro_id = (SELECT perro_id FROM perro WHERE nombre = ?) " +
                "AND fecha_cita = ? AND hora_cita = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Establecer los parámetros en la consulta SQL
            pstmt.setDate(1, Date.valueOf(cita.getFechaCita()));  // fecha_cita
            pstmt.setString(2, cita.getHoraCita());               // hora_cita
            pstmt.setString(3, cita.getEstado());                // estado (ahora será "Pendiente" si lo modificamos)
            pstmt.setString(4, cita.getNombreCliente());          // nombre_cliente
            pstmt.setString(5, cita.getNombrePerro());            // nombre_perro
            pstmt.setDate(6, Date.valueOf(fechaCitaOriginal));    // fecha original
            pstmt.setString(7, horaCitaOriginal);                // hora original

            // Ejecutar la actualización
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Si la actualización fue exitosa, mostrar un mensaje de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Cita modificada");
                alert.setContentText("Los cambios se han guardado correctamente.");
                alert.showAndWait();
            } else {
                // Si no se modificó ninguna fila, mostrar un mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudo modificar la cita");
                alert.setContentText("No se encontró una cita con los datos proporcionados.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Manejo de errores de base de datos
            Logger.getLogger(ModificarCitaClienteController.class.getName()).log(Level.SEVERE, "Error al actualizar la cita", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error de base de datos");
            alert.setContentText("Hubo un problema al guardar los cambios. Por favor, intente nuevamente.");
            alert.showAndWait();
        }

        // Recargar los datos de la tabla después de la actualización
        cargarDatos();
    }

    // Método para cargar los datos de la base de datos y refrescar la vista
    private void cargarDatos() {
        // Aquí va el código para cargar los datos de la base de datos y actualizar la vista
        // Asumiendo que tienes una tabla o alguna colección donde mostrar las citas

        // Datos de la conexión
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##PROYECTOINTEGRADO";
        String password = "123456";

        // Consulta SQL para cargar las citas desde la base de datos
        String query = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2, " +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id";


    }

    @FXML
    private void cancelarEdicion() {
        // Limpiar campos
        fechaCita.setValue(null);
        horaCita.setText("");

        // Cerrar la ventana
        fechaCita.getScene().getWindow().hide();
    }

}

