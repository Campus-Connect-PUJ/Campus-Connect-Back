package CampusConnect.CCBack.Wrappers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class WrapperHorario {

	public Long idUsuario;
	public Long idAsignatura;
	public LocalDateTime fechaInicial;
	public LocalDateTime fechaFinal;
	public String fechaInicio;
	public String fechaFin;
	public String lugar;

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

	public String getFenchaInicio() {
		return fechaInicio;
	}

	public void setFi(String fi) {
		this.fechaInicio = fi;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFf(String ff) {
		this.fechaFin= ff;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	

}
