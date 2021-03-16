package CampusConnect.CCBack.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class InformacionUsuario {

    @Id
    @GeneratedValue
    private Long id;

    private String identidadGenero;
    private String perfilContexto; // TODO: esto es una clase propia
    private String raza;
    private String lugarOrigen;
    private Date fechaNacimiento;

    // @OneToOne(mappedBy = "caracteristicasUsuario")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioGeneral usuario;

	public String getIdentidadGenero() {
		return identidadGenero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getPerfilContexto() {
		return perfilContexto;
	}
	public void setPerfilContexto(String perfilContexto) {
		this.perfilContexto = perfilContexto;
	}
	public String getLugarOrigen() {
		return lugarOrigen;
	}
	public void setLugarOrigen(String lugarOrigen) {
		this.lugarOrigen = lugarOrigen;
	}
	public void setIdentidadGenero(String identidadGenero) {
		this.identidadGenero = identidadGenero;
	}
	public UsuarioGeneral getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}
}
