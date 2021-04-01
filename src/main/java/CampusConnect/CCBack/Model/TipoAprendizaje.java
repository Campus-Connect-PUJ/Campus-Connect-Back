package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoAprendizaje {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    @JsonIgnore
    @ManyToMany(mappedBy = "estilosAprendizaje")
    private List<UsuarioGeneral> usuarios;

    @JsonIgnore
    @ManyToMany(mappedBy = "tiposAprendizaje")
    private List<Tip> tips;

    public TipoAprendizaje() {
        this.usuarios = new ArrayList<>();
        this.tips = new ArrayList<>();
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<UsuarioGeneral> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioGeneral> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tip> getTips() {
		return tips;
	}

	public void setTips(List<Tip> tips) {
		this.tips = tips;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
