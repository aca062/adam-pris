package model;

import controladores.ServletProyecto;

public class Requisito implements Comparable<Requisito>{
    private int id;
    private int esfuerzo;
    private String nombre;
    private int proyecto_id = ServletProyecto.proyecto;

    public Requisito(int id, int esfuerzo, String nombre) {
        this.id = id;
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
    }

    public Requisito(int esfuerzo, String nombre) {
        this.esfuerzo = esfuerzo;
        this.nombre = nombre;
    }

    public int getProyecto_id() {
        return proyecto_id;
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

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Requisito)) return false;
        Requisito requisito = (Requisito) obj;

        if (!this.nombre.equals(requisito.getNombre()) || this.esfuerzo != requisito.getEsfuerzo() || this.proyecto_id != requisito.getProyecto_id()) {
            return false;
        }
        return true;
    }
    
	@Override
	public int compareTo(Requisito o) {
	    if(this.getId() > o.getId())
	        return 1;
	    else if(this.getId() < o.getId())
	        return -1;
	    return this.getNombre().compareTo(o.getNombre());
	}
}
