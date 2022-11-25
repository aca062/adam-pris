package model;

public class Cliente implements Comparable<Cliente>{
    private int id;
    private int prioridad;
    private String nombre;
    private int usuario_id;

    public Cliente(int id, int prioridad, String nombre, int usuario_id) {
        this.id = id;
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }

    public Cliente(int prioridad, String nombre, int usuario_id) {
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }

    public Cliente(int prioridad, String nombre) {
        this.prioridad = prioridad;
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

        if (!this.nombre.equals(cliente.getNombre()) || this.prioridad != cliente.getPrioridad() || this.usuario_id != cliente.getUsuario_id()) {
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
