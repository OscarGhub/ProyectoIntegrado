package modelo;

public class SolicitudAdopcion {
    private String nombreCliente;
    private String nombrePerro;
    private String fechaAlta;
    private double donacion;
    private String estado;

    public SolicitudAdopcion(String nombreCliente, String nombrePerro, String fechaAlta, double donacion, String estado) {
        this.nombreCliente = nombreCliente;
        this.nombrePerro = nombrePerro;
        this.fechaAlta = fechaAlta;
        this.donacion = donacion;
        this.estado = estado;
    }

    public String getNombreCliente() { return nombreCliente; }
    public String getNombrePerro() { return nombrePerro; }
    public String getFechaAlta() { return fechaAlta; }
    public double getDonacion() { return donacion; }
    public String getEstado() { return estado; }
}
