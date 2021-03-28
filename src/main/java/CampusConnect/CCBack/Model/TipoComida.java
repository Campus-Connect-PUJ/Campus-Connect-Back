package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoComida {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @JsonIgnore
    @ManyToMany(mappedBy = "tiposComida")
    private List<Restaurante> restaurantes;

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

}
