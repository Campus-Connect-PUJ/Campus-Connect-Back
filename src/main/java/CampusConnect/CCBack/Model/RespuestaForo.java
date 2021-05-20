package CampusConnect.CCBack.Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RespuestaForo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private String texto;

    private Boolean reportado;

    private int puntaje;

    @ManyToOne
    @JoinColumn(name="idForo")
	@JsonIgnore
    private Foro foro;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

	@ManyToMany
    private List<UsuarioGeneral> usuariosGustaron;

	@ManyToMany
    private List<UsuarioGeneral> usuariosNoGustaron;

    public RespuestaForo () {
        this.reportado = false;
        this.puntaje = 0;
		this.fecha = LocalDateTime.now();
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Boolean getReportado() {
		return reportado;
	}

	public void setReportado(Boolean reportado) {
		this.reportado = reportado;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void agregarUsuarioGustaron(UsuarioGeneral ug){
        this.usuariosGustaron.add(ug);
    }
	
	public void agregarUsuarioNoGustaron(UsuarioGeneral ug){
        this.usuariosNoGustaron.add(ug);
    }

	public void quitarUsuarioGustaron(UsuarioGeneral ug){
        this.usuariosGustaron.remove(ug);
    }

    public void quitarUsuarioNoGustaron(UsuarioGeneral ug){
        this.usuariosNoGustaron.remove(ug);
    } 

}
