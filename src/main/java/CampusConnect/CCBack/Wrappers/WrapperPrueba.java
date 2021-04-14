package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

import CampusConnect.CCBack.Model.Tip;

public class WrapperPrueba {



    public List<Long> antecedentes;

	public List<Long> consecuencias;

	public long soporte;

	public long confianza;

	public long lift;

	public WrapperPrueba () {
		this.antecedentes = new ArrayList<Long>();
		this.consecuencias = new ArrayList<Long>();
    }

	public List<Long> getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(List<Long> antecedentes) {
		this.antecedentes = antecedentes;
	}

	public List<Long> getConsequents() {
		return consecuencias;
	}

	public void setConsequents(List<Long> consequents) {
		this.consecuencias = consequents;
	}

	public long getSoporte() {
		return soporte;
	}

	public void setSoporte(long soporte) {
		this.soporte = soporte;
	}

	public long getConfianza() {
		return confianza;
	}

	public void setConfianza(long confianza) {
		this.confianza = confianza;
	}

	public long getLift() {
		return lift;
	}

	public void setLift(long lift) {
		this.lift = lift;
	}

	


}
