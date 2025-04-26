package modelo;

public class Formulario {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fecha_nacimiento;
    private String telefono;
    private String correo;
    private String codigo_postal;
    private String localidad;
    private String provincia;
    private String pais;
    private TipoVia tipoVia;
    private String num_via;
    private String fechaCita;

    // Constructor sin parámetros para ser utilizado en el controlador
    public Formulario() {}

    // Constructor con parámetros

    public Formulario(String nombre, String apellido1, String apellido2, String fecha_nacimiento, String telefono, String correo, String codigo_postal, String localidad, String provincia, String pais, TipoVia tipoVia, String num_via, String fechaCita) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.codigo_postal = codigo_postal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipoVia = tipoVia;
        this.num_via = num_via;
        this.fechaCita = fechaCita;
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public TipoVia getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNum_via() {
        return num_via;
    }

    public void setNum_via(String num_via) {
        this.num_via = num_via;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    @Override
    public String toString() {
        return "Formulario{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", pais='" + pais + '\'' +
                ", tipoVia=" + tipoVia +
                ", num_via='" + num_via + '\'' +
                ", fechaCita='" + fechaCita + '\'' +
                '}';
    }
}
