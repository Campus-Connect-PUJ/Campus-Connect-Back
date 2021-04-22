package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tip implements Comparable<Tip>{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @JsonIgnore
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

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "UsuariosNoGustaronTip",
        joinColumns = @JoinColumn(name = "idTipNoGusto"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuariosNoGustaron;


    private int puntaje;

    public Tip() {
        this.tiposAprendizaje = new ArrayList<>();
        this.usuariosGustaron = new ArrayList<>();
        this.puntaje = 0;
    }

    public void like() {
        this.puntaje++;
    }

    public void dislike() {
        this.puntaje--;
    }

    public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

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

    public List<UsuarioGeneral> getUsuariosNoGustaron() {
		return usuariosNoGustaron;
	}

	public void setUsuariosNoGustaron(List<UsuarioGeneral> usuariosNoGustaron) {
		this.usuariosNoGustaron = usuariosNoGustaron;
	}

    public void agregaTipoAprendizaje(TipoAprendizaje ta) {
        this.tiposAprendizaje.add(ta);
    }

    public void agregarUsuarioGustaron(UsuarioGeneral ug){
        this.usuariosGustaron.add(ug);
    }

    public void agregarUsuarioNoGustaron(UsuarioGeneral ug){
        this.usuariosNoGustaron.add(ug);
    }

    @Override
    public int compareTo(Tip o) {
        if (this.puntaje > o.puntaje) {
            return -1;
        }
        if (this.puntaje < o.puntaje) {
            return 1;
        }
        return 0;
    }
}
