package modelo;

public class FormularioCita {
    private String correo_electronico;
    private String fecha_cita;
    private String donacion;
    private String hora_cita;
    private Perro perro;

    public FormularioCita(String correo_electronico, String fecha_cita, String donacion, String hora_cita, Perro perro) {
        this.correo_electronico = correo_electronico;
        this.fecha_cita = fecha_cita;
        this.donacion = donacion;
        this.hora_cita = hora_cita;
        this.perro = perro;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getDonacion() {
        return donacion;
    }

    public void setDonacion(String donacion) {
        this.donacion = donacion;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public Perro getPerro() {
        return perro;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    // Si solo necesitas el nombre del perro, puedes agregar este método.
    public String getNombrePerro() {
        return perro != null ? perro.getNombre() : null;
    }
}
