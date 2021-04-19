package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UsuarioMonitor {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private Asignatura asignatura;

	@JsonIgnore
    @OneToMany(mappedBy = "monitor")
    private List<Horario> horarios;

    public UsuarioMonitor() {
        this.horarios = new ArrayList<>();
    }

    public List<Horario> getHorario() {
		return horarios;
	}

	public void setHorario(List<Horario> horario) {
		this.horarios = horario;
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

}
