package modelo;

import java.time.LocalDate;

public class Protectora {
    private int protectora_id;
    private String cif;
    private String nombre;
    private String telefono;
    private String correo_electronico;
    private String localidad;
    private String provincia;
    private String pais;
    private String tipo_via;
    private String nombre_via;
    private String codigo_postal;
    private LocalDate fecha_alta;
    private LocalDate fecha_modificacion;

    public Protectora(int protectora_id, String cif, String nombre, String telefono, String correo_electronico, String localidad, String provincia, String pais, String tipo_via, String nombre_via, String codigo_postal, LocalDate fecha_alta, LocalDate fecha_modificacion) {
        this.protectora_id = protectora_id;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipo_via = tipo_via;
        this.nombre_via = nombre_via;
        this.codigo_postal = codigo_postal;
        this.fecha_alta = fecha_alta;
        this.fecha_modificacion = fecha_modificacion;
    }
    public int getProtectora_id() {
        return protectora_id;
    }
    public void setProtectora_id(int protectora_id) {
        this.protectora_id = protectora_id;
    }
    public String getCif() {
        return cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo_electronico() {
        return correo_electronico;
    }
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getTipo_via() {
        return tipo_via;
    }
    public void setTipo_via(String tipo_via) {
        this.tipo_via = tipo_via;
    }
    public String getNombre_via() {
        return nombre_via;
    }
    public void setNombre_via(String nombre_via) {
        this.nombre_via = nombre_via;
    }
    public String getCodigo_postal() {
        return codigo_postal;
    }
    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }
    public LocalDate getFecha_alta() {
        return fecha_alta;
    }
    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    public LocalDate getFecha_modificacion() {
        return fecha_modificacion;
    }
    public void setFecha_modificacion(LocalDate fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
