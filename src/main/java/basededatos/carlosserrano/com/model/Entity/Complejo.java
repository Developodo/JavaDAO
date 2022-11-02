package basededatos.carlosserrano.com.model.Entity;

public class Complejo {
	protected int id;
	protected String nombre;
	protected int superficie;
	protected Sede sede;
	
	public Complejo(int id, String nombre, int superficie) {
		this.id = id;
		this.nombre = nombre;
		this.superficie = superficie;
	}
	public Complejo() {
		this(-1,"",-1);
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
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
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
		Complejo other = (Complejo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Complejo [id=" + id + ", nombre=" + nombre + ", superficie=" + superficie + ", sede=" + sede.getId() + "]";
	}
	
	
	
	
	
}
