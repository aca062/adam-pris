package model;

public class Requisito {
    private int id;
    private int esfuerzo;
    private String nombre;
    private int usuario_id;

    public Requisito(int id, int esfuerzo, String nombre, int usuario_id) {
        this.id = id;
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }

    public Requisito(int esfuerzo, String nombre, int usuario_id) {
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }

    public Requisito(int esfuerzo, String nombre) {
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
        this.usuario_id = -1;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
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
