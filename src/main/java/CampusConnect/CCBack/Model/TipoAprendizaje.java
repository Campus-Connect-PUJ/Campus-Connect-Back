package CampusConnect.CCBack.Model;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
