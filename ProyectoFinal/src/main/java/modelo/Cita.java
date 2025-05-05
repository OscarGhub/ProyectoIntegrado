package modelo;

import java.time.LocalDate;

public class Cita {
    private int idCita;
    private double donacion;
    private String estado;
    private LocalDate fecha_cita;
    private String hora_cita;
    private int cliente_id;
    private LocalDate fecha_alta;
    private LocalDate fecha_modificacion;

    public Cita() {
    }

    public Cita(int idCita, double donacion, String estado, LocalDate fecha_cita, String hora_cita, int cliente_id, LocalDate fecha_alta, LocalDate fecha_modificacion) {
        this.idCita = idCita;
        this.donacion = donacion;
        this.estado = estado;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.cliente_id = cliente_id;
        this.fecha_alta = fecha_alta;
        this.fecha_modificacion = fecha_modificacion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public LocalDate getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(LocalDate fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
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
