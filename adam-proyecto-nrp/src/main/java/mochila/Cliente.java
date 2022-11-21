package mochila;

public class Cliente implements Comparable{
	private String nombre;
	private int prioridad;
	
	
	public Cliente() {
		
	}
	
	public Cliente(String nombre, int prioridad) {
		this.nombre=nombre;
		this.prioridad=prioridad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public int compareTo(Object o) {
		Cliente cli = (Cliente)o;
		
		if(cli.prioridad>this.prioridad)
			return -1;
		else if(cli.prioridad<this.prioridad)
			return 1;
		else
			return 0;
	}

	
	
}
