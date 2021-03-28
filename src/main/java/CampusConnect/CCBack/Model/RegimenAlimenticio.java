package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RegimenAlimenticio {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @JsonIgnore
    @OneToMany(mappedBy = "regimenAlimenticio")
    private List<RegimenAlimenticioUsuario> usuarios;

    @JsonIgnore
    @ManyToMany(mappedBy = "regimenesAlimenticios")
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

	public List<RegimenAlimenticioUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<RegimenAlimenticioUsuario> usuarios) {
		this.usuarios = usuarios;
	}
}
