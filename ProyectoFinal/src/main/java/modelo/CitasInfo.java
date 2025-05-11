package modelo;

import java.time.LocalDate;

public class CitasInfo {
    private double donacion;
    private String estado;
    private LocalDate fechaCita;
    private String horaCita;
    private String nombreCliente;
    private String apellido1;
    private String apellido2;
    private String correoUsuario;
    private String nombrePerro;

    public CitasInfo(double donacion, String estado, LocalDate fechaCita, String horaCita,
                     String nombreCliente, String apellido1, String apellido2,
                     String correoUsuario, String nombrePerro) {
        this.donacion = donacion;
        this.estado = estado;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.nombreCliente = nombreCliente;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correoUsuario = correoUsuario;
        this.nombrePerro = nombrePerro;
    }

    public double getDonacion() {
        return donacion;
    }

    public void setDonacion(double donacion) {
        this.donacion = donacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getNombrePerro() {
        return nombrePerro;
    }

    public void setNombrePerro(String nombrePerro) {
        this.nombrePerro = nombrePerro;
    }
}
