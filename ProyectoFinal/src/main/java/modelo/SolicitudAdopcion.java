package modelo;

public class SolicitudAdopcion {
    private String nombreCliente;
    private String nombrePerro;
    private String fechaAlta;
    private String estado;

    public SolicitudAdopcion(String nombreCliente, String nombrePerro, String fechaAlta, String estado) {
        this.nombreCliente = nombreCliente;
        this.nombrePerro = nombrePerro;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    public String getNombreCliente() { return nombreCliente; }
    public String getNombrePerro() { return nombrePerro; }
    public String getFechaAlta() { return fechaAlta; }
    public String getEstado() { return estado; }
}
