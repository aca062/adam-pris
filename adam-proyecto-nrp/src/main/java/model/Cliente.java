package model;

public class Cliente {
    private int id;
    private int prioridad;
    private String nombre;

    public Cliente(int id, int prioridad, String nombre) {
        this.id = id;
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    public Cliente(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
