package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TipoAprendizaje {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    @ManyToMany(mappedBy = "estiloAprendizaje")
    private List<UsuarioGeneral> usuarios;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
