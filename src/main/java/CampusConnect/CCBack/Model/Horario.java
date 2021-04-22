package CampusConnect.CCBack.Model;

import java.time.LocalDate;
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

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="idMonitor")
    private UsuarioMonitor monitor;

    private LocalDateTime fechaInicial;

	private LocalDateTime fechaFinal;

	public Horario(){

	}

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

	

}
