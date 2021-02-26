package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class GrupoEstudiantil {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String descripcion;
    private String tematicas;
    private float calificacion;
    private float requisitos;

    @ManyToMany
    @JoinTable (
        name = "CaracteristicasGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idGrupoEstudiantil"),
        inverseJoinColumns = @JoinColumn(name = "idCaracteristica"))
    private List<Caracteristica> caracteristicas;

	public String getNombre() {
		return nombre;
	}
	public float getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(float requisitos) {
		this.requisitos = requisitos;
	}
	public float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}
	public String getTematicas() {
		return tematicas;
	}
	public void setTematicas(String tematicas) {
		this.tematicas = tematicas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}
