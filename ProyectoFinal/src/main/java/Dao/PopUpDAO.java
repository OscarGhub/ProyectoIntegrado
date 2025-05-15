package Dao;

import modelo.CitasInfo;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PopUpDAO {

    public void actualizarCita(CitasInfo citaAntigua, CitasInfo citaNueva) throws Exception {
        String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                "WHERE fecha_cita = ? AND hora_cita = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(citaNueva.getFechaCita()));
            stmt.setString(2, citaNueva.getHoraCita());
            stmt.setString(3, citaNueva.getEstado());

            stmt.setDate(4, java.sql.Date.valueOf(citaAntigua.getFechaCita()));
            stmt.setString(5, citaAntigua.getHoraCita());

            stmt.executeUpdate();
        }
    }
}
