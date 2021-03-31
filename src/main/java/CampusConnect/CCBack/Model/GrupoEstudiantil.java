package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GrupoEstudiantil {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private float calificacion;

    @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Caracteristica> caracteristicas;

    @JsonIgnore
    @OneToMany(mappedBy = "grupoEstudiantil")
    private List<ResenhaGrupoEstudiantil> resenhas;

    @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Facultad> facultades;

    @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Requisito> requisitos;

    @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Tematica> tematicas;

	public String getNombre() {
		return nombre;
	}

	public float getCalificacion() {
		return calificacion;
	}

    public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
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

    public Long getId() {
        return this.id;
    }

    public List<ResenhaGrupoEstudiantil> getResenhas() {
		return resenhas;
	}

    public void setResenhas(List<ResenhaGrupoEstudiantil> resenhas) {
		this.resenhas = resenhas;
	}

    public List<Facultad> getFacultades() {
		return facultades;
	}

    public void setFacultades(List<Facultad> facultades) {
		this.facultades = facultades;
	}

    public List<Requisito> getRequisitos() {
		return requisitos;
	}

    public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public List<Tematica> getTematicas() {
		return tematicas;
	}

	public void setTematicas(List<Tematica> tematicas) {
		this.tematicas = tematicas;
	}

}
