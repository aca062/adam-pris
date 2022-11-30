package model;

public class Cliente implements Comparable<Cliente>{
    private int id;
    private int prioridad;
    private String nombre;
    private int proyecto_id;

    public Cliente(int id, int prioridad, String nombre, int proyecto_id) {
        this.id = id;
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.proyecto_id = proyecto_id;
    }

    public Cliente(int prioridad, String nombre, int proyecto_id) {
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.proyecto_id = proyecto_id;
    }

    public int getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(int proyecto_id) {
        this.proyecto_id = proyecto_id;
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

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Cliente)) return false;
        Cliente cliente = (Cliente) obj;

        if (!this.nombre.equals(cliente.getNombre()) || this.prioridad != cliente.getPrioridad() || this.proyecto_id != cliente.getProyecto_id()) {
            return false;
        }
        return true;
    }

	@Override
	public int compareTo(Cliente o) {
	    if(this.getId() > o.getId())
	        return 1;
	    else if(this.getId() < o.getId())
	        return -1;
	    return this.getNombre().compareTo(o.getNombre());
	}
}
