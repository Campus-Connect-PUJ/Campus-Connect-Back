package CampusConnect.CCBack.Wrappers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperMonitoria {

	public Long idUsuario;
	public Long idAsignatura;

	public WrapperMonitoria () {

		
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
	
}
