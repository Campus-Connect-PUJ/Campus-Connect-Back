package CampusConnect.CCBack.Wrappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperMonitoria {

	public Long idUsuario;

	public String asignatura;

	public LocalDateTime fechaInicial;

	public LocalDateTime fechaFinal;

	public WrapperMonitoria () {

    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public LocalDateTime getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaIncial(LocalDateTime fechaIncial) {
		this.fechaInicial = fechaIncial;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	

}
