package basededatos.carlosserrano.com.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Sede {
	protected int id;
	protected String nombre;
	protected List<Complejo> complejos;
	
	public Sede(int id, String nombre) {
		this.id=id; this.nombre=nombre;
	}
	public Sede() {
		this(-1,"");
	}
	public Sede(int id) {
		this.id=id;
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
	public List<Complejo> getComplejos() {
		return complejos;
	}
	public void setComplejos(List<Complejo> complejos) {
		this.complejos = complejos;
	}
	public void addComplejo(Complejo c) {
		if(this.complejos==null) this.complejos=new ArrayList<Complejo>();
		this.complejos.add(c);
	}
	@Override
	public String toString() {
		String result = "Sede [id=" + id + ", nombre=" + nombre + "]";
		result+=getComplejos();
		return result;
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
