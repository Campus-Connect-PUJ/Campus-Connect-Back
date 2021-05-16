package CampusConnect.CCBack.Model;

public class ReglaAsociacionConPuntaje implements Comparable<ReglaAsociacionConPuntaje>{

	private float puntaje;

	private ReglasDeAsociacion regla;

	public ReglaAsociacionConPuntaje(){

	}

	public float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	public ReglasDeAsociacion getRegla() {
		return regla;
	}

	public void setRegla(ReglasDeAsociacion regla) {
		this.regla = regla;
	}

	@Override
    public int compareTo(ReglaAsociacionConPuntaje o) {
        if (this.getPuntaje() > o.getPuntaje()) {
            return -1;
        }
        if (this.getPuntaje() < o.getPuntaje()) {
            return 1;
        }
        return 0;
    }

}
