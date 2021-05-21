package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GrupoEstudiantil {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private float calificacion;

    private String contacto;

    // @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Caracteristica> caracteristicas;

    @JsonIgnore
    @OneToMany(mappedBy = "grupoEstudiantil")
    private List<ResenhaGrupoEstudiantil> resenhas;

    // @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Facultad> facultades;

    @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Requisito> requisitos;

    // @JsonIgnore
    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Tematica> tematicas;

    @ManyToMany(mappedBy = "gruposEstudiantiles")
    private List<Actividad> actividades;

    public GrupoEstudiantil() {
        this.facultades      = new ArrayList<>();
        this.requisitos      = new ArrayList<>();
        this.tematicas       = new ArrayList<>();
        this.caracteristicas = new ArrayList<>();
        this.resenhas        = new ArrayList<>();
    }

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

    public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
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

	public void setId(Long id) {
		this.id = id;
	}

    public void agregarCaracteristica(Caracteristica c) {
        this.caracteristicas.add(c);
    }

    public void agregarTematica(Tematica t) {
        this.tematicas.add(t);
    }

    public void agregarFacultad(Facultad f) {
        this.facultades.add(f);
    }

    public void agregarRequisito(Requisito r) {
        this.requisitos.add(r);
    }

    public void agregarResenha(ResenhaGrupoEstudiantil r) {
        this.resenhas.add(r);
    }

    public void setContacto(String contacto){
        this.contacto= contacto;
    }

    public String getContacto(){
        return this.contacto;
    }

}
