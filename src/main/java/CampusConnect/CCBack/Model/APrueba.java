package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APrueba implements Comparable<APrueba>{

	private int puntaje;

	private ReglasDeAsociacion regla;


	public APrueba(){

	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public ReglasDeAsociacion getRegla() {
		return regla;
	}

	public void setRegla(ReglasDeAsociacion regla) {
		this.regla = regla;
	}

	@Override
    public int compareTo(APrueba o) {
        if (this.getRegla().getLift() > o.getRegla().getLift()) {
            return -1;
        }
        if (this.getRegla().getLift() < o.getRegla().getLift()) {
            return 1;
        }
        return 0;
    }


}
