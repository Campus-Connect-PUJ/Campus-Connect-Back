package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoComida {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    @JsonIgnore
    @ManyToMany(mappedBy = "tiposComida")
    private List<Restaurante> restaurantes;

    public TipoComida() {
        this.restaurantes = new ArrayList<>();
    }

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
