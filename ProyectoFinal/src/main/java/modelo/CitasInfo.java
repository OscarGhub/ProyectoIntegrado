package modelo;

import java.time.LocalDate;

public class CitasInfo {
    private int idCita;
    private double donacion;
    private String estado;
    private LocalDate fechaCita;
    private String nombreCliente;
    private String apellido1;
    private String apellido2;
    private String correoCliente;
    private String correoUsuario;
    private String nombrePerro;

    // Constructor vacío (para permitir la creación sin parámetros)
    public CitasInfo() {
    }

    // Constructor completo (para crear objetos con todos los atributos)
    public CitasInfo( double donacion, String estado, LocalDate fechaCita,
                     String nombreCliente, String apellido1, String apellido2,
                     String correoCliente, String correoUsuario, String nombrePerro) {
        this.donacion = donacion;
        this.estado = estado;
        this.fechaCita = fechaCita;
        this.nombreCliente = nombreCliente;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correoCliente = correoCliente;
        this.correoUsuario = correoUsuario;
        this.nombrePerro = nombrePerro;
    }

    // Getters y Setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public double getDonacion() { return donacion; }
    public void setDonacion(double donacion) { this.donacion = donacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaCita() { return fechaCita; }
    public void setFechaCita(LocalDate fechaCita) { this.fechaCita = fechaCita; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }

    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }

    public String getNombrePerro() { return nombrePerro; }
    public void setNombrePerro(String nombrePerro) { this.nombrePerro = nombrePerro; }
}
