package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UsuarioMonitor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private Asignatura asignatura;

    @OneToMany(mappedBy = "monitor")
    private List<Horario> horarios;

	private Long calificacion;

	private Long cantidadVotos;


	

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificaciones) {
		this.calificacion = calificaciones;
	}

	public Long getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(Long cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}


	public UsuarioMonitor() {
        this.horarios = new ArrayList<>();
    }


	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addHorario(Horario horario){
		this.horarios.add(horario);
	}

	public void quitarHorario(Horario horario){
		this.horarios.remove(horario);
	}

}
