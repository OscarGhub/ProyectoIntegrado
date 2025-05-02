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


    public Perro(int perro_id, String nombre, String raza, Sexo sexo, boolean adoptado, LocalDate fecha_alta, LocalDate fecha_modificacion){
     this.perro_id = perro_id;
     this.nombre = nombre;
     this.raza = raza;
     this.sexo = sexo;
     this.adoptado = adoptado;
     this.fecha_alta = fecha_alta;
     this.fecha_modificacion = fecha_modificacion;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
