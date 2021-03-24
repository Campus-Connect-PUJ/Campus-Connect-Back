package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RegimenAlimenticio {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @JsonIgnore
    @ManyToMany(mappedBy = "tiposRestaurante")
    private List<Restaurante> restaurantes;

	public String getTipo() {
		return tipo;
	}

	public List<Restaurante> getActividades() {
		return restaurantes;
	}

	public void setActividades(List<Restaurante> actividades) {
		this.restaurantes = actividades;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}
}
