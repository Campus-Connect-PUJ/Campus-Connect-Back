package CampusConnect.CCBack.Wrappers;

import java.util.List;

import CampusConnect.CCBack.Model.GrupoEstudiantil;

public class WrapperGrupoEstudiantil {

    public GrupoEstudiantil grupoEstudiantil;

    public List<Long> caracteristicas;

    public List<Long> tematicas;

    public List<Long> facultades;

    public List<Long> requisitos;

    public List<Long> getFacultades() {
		return facultades;
	}

    public void setFacultades(List<Long> facultades) {
		this.facultades = facultades;
	}

    public List<Long> getCaracteristicas() {
		return caracteristicas;
	}

    public void setCaracteristicas(List<Long> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

    public List<Long> getTematicas() {
		return tematicas;
	}

    public void setTematicas(List<Long> tematicas) {
		this.tematicas = tematicas;
	}

    public GrupoEstudiantil getGrupoEstudiantil() {
		return grupoEstudiantil;
	}

	public void setGrupoEstudiantil(GrupoEstudiantil grupoEstudiantil) {
		this.grupoEstudiantil = grupoEstudiantil;
	}

	public List<Long> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Long> requisitos) {
		this.requisitos = requisitos;
	}

}
