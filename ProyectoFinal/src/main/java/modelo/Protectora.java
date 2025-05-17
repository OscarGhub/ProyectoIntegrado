package modelo;

public class Protectora extends Usuario {

    public Protectora(String nombreUsuario, String nombreVia, String tipoVia, String pais, String provincia,
                      String localidad, String codigoPostal, String telefono, String correoElectronico, String contrasena) {
        setNombreUsuario(nombreUsuario);
        setNombreVia(nombreVia);
        setTipoVia(tipoVia);
        setPais(pais);
        setProvincia(provincia);
        setLocalidad(localidad);
        setCodigoPostal(codigoPostal);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        setContrasena(contrasena);
    }

    public Protectora() {
        super();
    }
}
