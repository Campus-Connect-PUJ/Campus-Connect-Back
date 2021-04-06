package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoRestaurante {

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

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void agregarRestaurante(Restaurante res) {
        this.restaurantes.add(res);
	}
}
