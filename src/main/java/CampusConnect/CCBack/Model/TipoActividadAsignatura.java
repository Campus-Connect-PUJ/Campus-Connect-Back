package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TipoActividadAsignatura {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @JsonIgnore
    @ManyToMany(mappedBy = "tiposActividad")
    private List<ActividadAsignatura> actividades;

	public String getTipo() {
		return tipo;
	}

	public List<ActividadAsignatura> getActividadesAsignatura() {
		return actividades;
	}

	public void setActividadesAsignatura(List<ActividadAsignatura> actividades) {
		this.actividades = actividades;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}
}
