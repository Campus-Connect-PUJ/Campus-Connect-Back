package CampusConnect.CCBack.Wrappers;

import java.util.ArrayList;
import java.util.List;

public class WrapperReglaAsociacion {



    public List<Long> antecedentes;

	public List<Long> consecuencias;

	public float soporte;

	public float confianza;

	public float lift;

	public WrapperReglaAsociacion () {
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

	public float getSoporte() {
		return soporte;
	}

	public void setSoporte(float soporte) {
		this.soporte = soporte;
	}

	public float getConfianza() {
		return confianza;
	}

	public void setConfianza(float confianza) {
		this.confianza = confianza;
	}

	public float getLift() {
		return lift;
	}

	public void setLift(float lift) {
		this.lift = lift;
	}

	


}
