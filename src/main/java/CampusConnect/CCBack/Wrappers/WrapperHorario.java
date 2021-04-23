package CampusConnect.CCBack.Wrappers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperHorario {

	public Long idUsuario;
	public Long idAsignatura;
	public LocalDateTime fechaInicial;
	public LocalDateTime fechaFinal;

	public WrapperHorario () {

		
    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Long idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public LocalDateTime getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(LocalDateTime fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	

}
