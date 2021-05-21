package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<UsuarioMonitor> monitores;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<ActividadAsignatura> actividades;

    @JsonIgnore
    @OneToMany(mappedBy = "asignatura")
    private List<MaterialEstudio> materialEstudio;

	public Asignatura(){
		this.monitores = new ArrayList<>();
		this.actividades = new ArrayList<>();
		this.materialEstudio = new ArrayList<>();
	}

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

	public List<ActividadAsignatura> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadAsignatura> actividades) {
		this.actividades = actividades;
	}

	public List<MaterialEstudio> getMaterialEstudio() {
		return materialEstudio;
	}

	public void setMaterialEstudio(List<MaterialEstudio> materialEstudio) {
		this.materialEstudio = materialEstudio;
	}

	public void addMonitor(UsuarioMonitor monitor) {
		this.monitores.add(monitor);
	}
}
