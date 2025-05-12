package Dao;

import modelo.Alertas;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IniciarSesionProtectoraDao {

    public static boolean inicioSesionProtectora(String usuario, String contrasenia) {
        boolean resultado = false;

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {


            String query = "SELECT * FROM usuario_protectora WHERE nombre_usuario = ? AND contrasena = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, usuario);
                statement.setString(2, contrasenia);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    resultado = true;
                } else {
                    Alertas.mostrarAlertaError(null, "Error", "Usuario o contraseña incorrectos.");
                }

            } catch (Exception e) {
                Alertas.mostrarAlertaError(null, "Error", "Error en la consulta.");
                e.printStackTrace();
            }

        } catch (Exception e) {
            Alertas.mostrarAlertaError(null, "Error", "Error al conectar con la base de datos.");
            e.printStackTrace();
        }

        return resultado;
    }

}
