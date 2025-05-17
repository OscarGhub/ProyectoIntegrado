package modelo;

public class Protectora extends Usuario {

    private String nombre;

    public Protectora(String nombre, String nombreUsuario, String nombreVia, String tipoVia, String pais, String provincia,
                      String localidad, String codigoPostal, String telefono, String correoElectronico, String contrasena) {
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
