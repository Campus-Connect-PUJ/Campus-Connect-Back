package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "UsuariosGustaronTip",
        joinColumns = @JoinColumn(name = "idTipGusto"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuariosGustaron;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    public Long getId() {
        return this.id;
    }

	public List<TipoAprendizaje> getTiposAprendizaje() {
		return tiposAprendizaje;
	}

	public void setTiposAprendizaje(List<TipoAprendizaje> tiposAprendizaje) {
		this.tiposAprendizaje = tiposAprendizaje;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioGeneral> getUsuariosGustaron() {
		return usuariosGustaron;
	}

	public void setUsuariosGustaron(List<UsuarioGeneral> usuariosGustaron) {
		this.usuariosGustaron = usuariosGustaron;
	}
}
