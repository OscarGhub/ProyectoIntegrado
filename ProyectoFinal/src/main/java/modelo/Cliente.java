package modelo;

import java.time.LocalDate;

public class Cliente {
    private int cliente_id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo_electronico;
    private String telefono;
    private String direccion;
    private LocalDate fecha_nacimiento;
    private String codigo_postal;
    private String localidad;
    private String provincia;
    private String pais;
    private String tipo_via;
    private String nombre_via;
    private LocalDate fecha_alta;
    private LocalDate fecha_modificacion;

    public Cliente(int cliente_id, String nombre,String apellido1, String apellido2, String correo_electronico, String telefono, String direccion, LocalDate fecha_nacimiento, String codigo_postal, String localidad, String provincia, String pais, String tipo_via, String nombre_via, LocalDate fecha_alta, LocalDate fecha_modificacion){
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo_electronico = correo_electronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.codigo_postal = codigo_postal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipo_via = tipo_via;
        this.nombre_via = nombre_via;
        this.fecha_alta = fecha_alta;
        this.fecha_modificacion = fecha_modificacion;
    }
    public int getCliente_id() {
        return cliente_id;
    }
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    public String getCorreo_electronico() {
        return correo_electronico;
    }
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getCodigo_postal() {
        return codigo_postal;
    }
    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
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
