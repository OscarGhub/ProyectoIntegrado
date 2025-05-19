package Dao;

import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModificarAdpDao {

    public static void actualizarEstadoSolicitud(String nombrePerro, String nombreCliente, String nuevoEstado) {
        String updateSolicitud = """
        UPDATE solicitud_adopcion
        SET estado = ?
        WHERE perro_id = (SELECT perro_id FROM perro WHERE nombre = ?)
        AND cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?)
    """;

        String updatePerro = """
        UPDATE perro
        SET cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?)
        WHERE nombre = ?
    """;

        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false); // iniciar transacción

            try (PreparedStatement stmt1 = conn.prepareStatement(updateSolicitud)) {
                stmt1.setString(1, nuevoEstado);
                stmt1.setString(2, nombrePerro);
                stmt1.setString(3, nombreCliente);
                stmt1.executeUpdate();
            }

            // Si es aceptada, asignar cliente_id al perro
            if (nuevoEstado.equalsIgnoreCase("aceptada")) {
                try (PreparedStatement stmt2 = conn.prepareStatement(updatePerro)) {
                    stmt2.setString(1, nombreCliente);
                    stmt2.setString(2, nombrePerro);
                    stmt2.executeUpdate();
                }
            }

            conn.commit(); // confirmar cambios

        } catch (SQLException e) {
            e.printStackTrace();
            // Idealmente hacer rollback aquí si conn no es null
        }
    }
}
