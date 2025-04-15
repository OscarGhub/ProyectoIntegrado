package com.Model;

public class Frutas {

    private String nombre;
    private String color;
    private int precio_kg;
    private int proveedor_id;

    public Frutas(String nombre, String color, int precio_kg, int proveedor_id) {
        this.nombre = nombre;
        this.color = color;
        this.precio_kg = precio_kg;
        this.proveedor_id = proveedor_id;

    }
    public Frutas() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio_kg() {
        return precio_kg;
    }

    public void setPrecio_kg(int precio_kg) {
        this.precio_kg = precio_kg;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }
}
