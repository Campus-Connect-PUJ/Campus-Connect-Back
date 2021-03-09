package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Tip {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    // @JsonIgnore // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    @ManyToMany
    @JoinTable (
        name = "TipoAprendizajeTip",
        joinColumns = @JoinColumn(name = "idTip"),
        inverseJoinColumns = @JoinColumn(name = "idTipoAprendizaje"))
    private List<TipoAprendizaje> tiposAprendizaje;

	public List<TipoAprendizaje> getRespuestas() {
		return tiposAprendizaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setRespuestas(List<TipoAprendizaje> respuestas) {
		this.tiposAprendizaje = respuestas;
	}
}
