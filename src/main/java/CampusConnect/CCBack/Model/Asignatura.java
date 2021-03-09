package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToMany
    @JoinTable (
        name = "UsuarioMonitor",
        joinColumns = @JoinColumn(name = "idAsignatura"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> monitores;

    @OneToMany(mappedBy = "asignatura")
    private List<Actividad> actividades;

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

	public List<UsuarioGeneral> getMonitores() {
		return monitores;
	}

	public void setMonitores(List<UsuarioGeneral> monitores) {
		this.monitores = monitores;
	}

	public List<Actividad> getActividades() {
		return actividades;
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
