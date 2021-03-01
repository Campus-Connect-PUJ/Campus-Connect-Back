package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Actividad {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private LocalTime fecha;

    private float duracion;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private Asignatura asignatura;

    @ManyToMany
    @JoinTable (
        name = "TiposActividad",
        joinColumns = @JoinColumn(name = "idActividad"),
        inverseJoinColumns = @JoinColumn(name = "idTipoActividad"))
    private List<TipoActividad> tiposActividad;

	public String getNombre() {
		return nombre;
	}

	public List<TipoActividad> getTiposActividad() {
		return tiposActividad;
	}

	public void setTiposActividad(List<TipoActividad> tiposActividad) {
		this.tiposActividad = tiposActividad;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public LocalTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
