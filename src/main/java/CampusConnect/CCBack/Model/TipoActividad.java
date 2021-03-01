package CampusConnect.CCBack.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TipoActividad {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @ManyToMany(mappedBy = "tiposActividad")
    private List<Actividad> actividades;

	public String getTipo() {
		return tipo;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}
}
