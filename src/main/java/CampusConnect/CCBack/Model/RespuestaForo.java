package CampusConnect.CCBack.Model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RespuestaForo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime fecha;

    @ManyToOne
    @JoinColumn(name="idForo")
	@JsonIgnore
    private Foro foro;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    private String texto;

    private Boolean reportado;

    private int puntaje;

    public RespuestaForo () {
        this.reportado = false;
        this.puntaje = 0;
		this.fecha = LocalTime.now();
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

	public LocalTime getFecha() {
		return fecha;
	}

	public Boolean getReportado() {
		return reportado;
	}

	public void setReportado(Boolean reportado) {
		this.reportado = reportado;
	}

	public void setFecha(LocalTime fecha) {
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

}
