package CampusConnect.CCBack.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RegimenAlimenticioUsuario {

    @Id
    @GeneratedValue
    private Long id;

    private int exigencia;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioGeneral usuario;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private RegimenAlimenticio regimenAlimenticio;

	public int getExigencia() {
		return exigencia;
	}

	public RegimenAlimenticio getRegimenAlimenticio() {
		return regimenAlimenticio;
	}

	public void setRegimenAlimenticio(RegimenAlimenticio regimenAlimenticio) {
		this.regimenAlimenticio = regimenAlimenticio;
	}

	public void setExigencia(int exigencia) {
		this.exigencia = exigencia;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

}
