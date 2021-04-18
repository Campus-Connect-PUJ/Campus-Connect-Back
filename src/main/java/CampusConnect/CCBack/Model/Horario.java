package CampusConnect.CCBack.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private UsuarioMonitor monitor;

    private LocalDate fecha;

	private Long duracion;

    public Long getId() {
		return id;
	}

    public Long getDuracion() {
		return duracion;
	}

    public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

    public UsuarioMonitor getMonitor() {
		return monitor;
	}

    public void setMonitor(UsuarioMonitor monitor) {
		this.monitor = monitor;
	}

    public void setId(Long id) {
		this.id = id;
	}

    public LocalDate getFecha() {
		return fecha;
	}

    public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
