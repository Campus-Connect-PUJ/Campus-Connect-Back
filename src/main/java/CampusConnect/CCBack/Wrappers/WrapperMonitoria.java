package CampusConnect.CCBack.Wrappers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperMonitoria {

	public Long idUsuario;

	public WrapperAsignatura asignatura;

	public List<WrapperHorarios> horarios;

	public WrapperMonitoria () {
		this.horarios = new ArrayList<>();
    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public WrapperAsignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(WrapperAsignatura asignatura) {
		this.asignatura = asignatura;
	}

	public List<WrapperHorarios> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<WrapperHorarios> horarios) {
		this.horarios = horarios;
	}

	


	

}
