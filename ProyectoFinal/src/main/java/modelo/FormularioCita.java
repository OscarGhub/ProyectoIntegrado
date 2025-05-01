package modelo;

public class FormularioCita {
    private String correo_electronico;
    private String fecha_cita;
    private String donacion;

    public FormularioCita(String correo_electronico, String fecha_cita, String donacion) {
        this.correo_electronico = correo_electronico;
        this.fecha_cita = fecha_cita;
        this.donacion = donacion;
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

    @Override
    public String toString() {
        return "FormularioCita{" +
                "correo_electronico='" + correo_electronico + '\'' +
                ", fecha_cita=" + fecha_cita +
                ", donacion=" + donacion +
                '}';
    }
}
