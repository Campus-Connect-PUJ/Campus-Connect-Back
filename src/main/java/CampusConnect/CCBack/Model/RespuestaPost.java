package CampusConnect.CCBack.Model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RespuestaPost {

    @Id
    @GeneratedValue
    private Long id;

    private LocalTime fecha;

    @ManyToOne
    @JoinColumn(name="idPost")
    private Post post;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

    private String texto;

    private Boolean reportado;

    RespuestaPost () {
        this.reportado = false;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
