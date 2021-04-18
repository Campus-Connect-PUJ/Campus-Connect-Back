package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<UsuarioMonitor> monitores;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<Actividad> actividades;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<MaterialEstudio> materialEstudio;

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UsuarioMonitor> getMonitores() {
		return monitores;
	}

	public void setMonitores(List<UsuarioMonitor> monitores) {
		this.monitores = monitores;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<MaterialEstudio> getMaterialEstudio() {
		return materialEstudio;
	}

	public void setMaterialEstudio(List<MaterialEstudio> materialEstudio) {
		this.materialEstudio = materialEstudio;
	}
}
