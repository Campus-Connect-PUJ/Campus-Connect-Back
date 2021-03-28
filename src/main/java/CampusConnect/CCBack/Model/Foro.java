package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Foro {

    @Id
    @GeneratedValue
    private Long id;

    private LocalTime fecha;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    private String titulo;

    private String descripcion;

    private Boolean reportado;

    private int puntaje;

    @JsonIgnore
    @ManyToMany(mappedBy = "foro")
    private List<RespuestaForo> respuestas;

    public Foro () {
        this.reportado = false;
        this.respuestas = new ArrayList<RespuestaForo>();
        this.fecha = LocalTime.now();
        this.puntaje = 0;
    }

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalTime getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<RespuestaForo> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaForo> respuestas) {
		this.respuestas = respuestas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}