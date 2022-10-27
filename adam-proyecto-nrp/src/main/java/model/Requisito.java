package model;

public class Requisito {
    private int id;
    private int esfuerzo;
    private String nombre;

    public Requisito(int id, int esfuerzo, String nombre) {
        this.id = id;
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
    }

    public Requisito(int esfuerzo, String nombre) {
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
