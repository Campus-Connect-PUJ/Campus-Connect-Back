package CampusConnect.CCBack.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Horario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicial;

	private LocalDateTime fechaFinal;

	public String lugar;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="idMonitor")
    private UsuarioMonitor monitor;

    public Long getId() {
		return id;
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

	public LocalDateTime getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(LocalDateTime fechaInicial) {
		System.out.println("-z " + fechaInicial);
		this.fechaInicial = fechaInicial;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
