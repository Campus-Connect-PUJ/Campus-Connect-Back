package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ActividadAsignatura {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // TODO: esto se podria conectar con la clase Horario
    private LocalTime fecha;

    private float duracion;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private Asignatura asignatura;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "TiposActividadAsignatura",
        joinColumns = @JoinColumn(name = "idActividadAsignatura"),
        inverseJoinColumns = @JoinColumn(name = "idTipoActividad"))
    private List<TipoActividadAsignatura> tiposActividad;

	public String getNombre() {
		return nombre;
	}

	public List<TipoActividadAsignatura> getTiposActividad() {
		return tiposActividad;
	}

	public void setTiposActividad(List<TipoActividadAsignatura> tiposActividad) {
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
