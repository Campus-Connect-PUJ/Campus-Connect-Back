package CampusConnect.CCBack.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ResenhaGrupoEstudiantil {

    @Id
    @GeneratedValue
    private Long id;

    private float estrellas;

    @ManyToOne
    @JoinColumn(name="idGrupoEstudiantil")
    private GrupoEstudiantil grupoEstudiantil;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

	public float getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(float estrellas) {
		this.estrellas = estrellas;
	}

	public GrupoEstudiantil getGrupoEstudiantil() {
		return grupoEstudiantil;
	}

	public void setGrupoEstudiantil(GrupoEstudiantil grupoEstudiantil) {
		this.grupoEstudiantil = grupoEstudiantil;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
