package basededatos.carlosserrano.com.model.Entity;

public class Sede {
	protected int id;
	protected String nombre;
	
	public Sede(int id, String nombre) {
		this.id=id; this.nombre=nombre;
	}
	public Sede() {
		this(-1,"");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Sede [id=" + id + ", nombre=" + nombre + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sede other = (Sede) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
