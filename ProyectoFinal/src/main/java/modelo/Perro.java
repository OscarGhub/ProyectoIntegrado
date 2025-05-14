package modelo;

import java.time.LocalDate;

public class Perro {
    private int perro_id;
    private String nombre;
    private String raza;
    private Sexo sexo;
    private boolean adoptado;
    private LocalDate fecha_alta;
    private LocalDate fecha_modificacion;

    // Constructor completo
    public Perro(int perro_id, String nombre, String raza, Sexo sexo, boolean adoptado, LocalDate fecha_alta, LocalDate fecha_modificacion) {
        this.perro_id = perro_id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.adoptado = adoptado;
        this.fecha_alta = fecha_alta;
        this.fecha_modificacion = fecha_modificacion;
    }

    // Constructor simple (por si solo necesitas pasar algunos datos a un formulario)
    public Perro(String nombre, String raza, Sexo sexo) {
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
    }

    public int getPerro_id() {
        return perro_id;
    }

    public void setPerro_id(int perro_id) {
        this.perro_id = perro_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public boolean isAdoptado() {
        return adoptado;
    }

    public void setAdoptado(boolean adoptado) {
        this.adoptado = adoptado;
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

    public String getSexoComoString() {
        return (sexo != null) ? sexo.name() : "";
    }

    @Override
    public String toString() {
        return nombre + " (" + raza + ")";
    }
}
