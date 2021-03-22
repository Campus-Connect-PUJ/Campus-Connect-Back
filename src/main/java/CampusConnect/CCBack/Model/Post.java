package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private LocalTime fecha;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    private String descripcion;

    private Boolean reportado;

    @ManyToMany(mappedBy = "post")
    private List<RespuestaPost> respuestas;

    Post () {
        this.reportado = false;
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

	public List<RespuestaPost> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaPost> respuestas) {
		this.respuestas = respuestas;
	}

}
