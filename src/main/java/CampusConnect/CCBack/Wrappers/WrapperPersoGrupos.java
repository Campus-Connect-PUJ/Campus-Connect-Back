package CampusConnect.CCBack.Wrappers;

import java.util.List;

public class WrapperPersoGrupos {

    // Una lista de características, temáticas, string actividades
    // hobbies u el bool de si cree el Dios

    private List<Long> caracteristicas;
    private List<Long> tematicas;

    private List<String> hobbies;
    private List<String> actividades;

	public List<String> getActividades() {
		return actividades;
	}

	public void setActividades(List<String> actividades) {
		this.actividades = actividades;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public List<Long> getTematicas() {
		return tematicas;
	}

	public void setTematicas(List<Long> tematicas) {
		this.tematicas = tematicas;
	}

	public List<Long> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Long> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

}
