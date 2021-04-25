package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperTip {

    public Tip tip;

    public Long idUsuario;

    public List<Long> tiposAprendizaje;

	public Long nivelExigencia;

    public WrapperTip() {
        this.tiposAprendizaje = new ArrayList<>();
    }

	public List<Long> getTiposAprendizaje() {
		return tiposAprendizaje;
	}

	public void setTiposAprendizaje(List<Long> tiposAprendizaje) {
		this.tiposAprendizaje = tiposAprendizaje;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getExigencia() {
		return nivelExigencia;
	}

	public void setExigencia(Long exigencia) {
		this.nivelExigencia = exigencia;
	}


}
