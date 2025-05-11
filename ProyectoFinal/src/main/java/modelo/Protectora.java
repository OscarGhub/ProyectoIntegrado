package modelo;

import java.time.LocalDate;

public class Protectora {
    private String nombreUsuario;
    private String contrasena;
    private String correoElectronico;
    private String telefono;
    private String codigoPostal;
    private String localidad;
    private String provincia;
    private String pais;
    private String tipoVia;
    private String nombreVia;

    public Protectora(String nombreUsuario, String nombreVia, String tipoVia, String pais, String provincia, String localidad, String codigoPostal, String telefono, String correoElectronico, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.nombreVia = nombreVia;
        this.tipoVia = tipoVia;
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    public Protectora() {}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
