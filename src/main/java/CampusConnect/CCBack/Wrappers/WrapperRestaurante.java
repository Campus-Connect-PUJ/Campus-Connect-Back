package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Restaurante;

public class WrapperRestaurante {

    public Restaurante restaurante;

    private Long idLugar;

    public List<Long> tiposRestaurante;

    public List<Long> regimenesAlimenticios;

    public List<Long> tiposComida;

    public WrapperRestaurante () {
        this.tiposRestaurante = new ArrayList<>();
        this.regimenesAlimenticios = new ArrayList<>();
        this.tiposComida = new ArrayList<>();
    }

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}

	public List<Long> getTiposRestaurante() {
		return tiposRestaurante;
	}

	public void setTiposRestaurante(List<Long> tiposRestaurante) {
		this.tiposRestaurante = tiposRestaurante;
	}

	public List<Long> getRegimenesAlimenticios() {
		return regimenesAlimenticios;
	}

	public void setRegimenesAlimenticios(List<Long> regimenesAlimenticios) {
		this.regimenesAlimenticios = regimenesAlimenticios;
	}

	public List<Long> getTiposComida() {
		return tiposComida;
	}

	public void setTiposComida(List<Long> tiposComida) {
		this.tiposComida = tiposComida;
	}

}
