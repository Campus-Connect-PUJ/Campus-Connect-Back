package CampusConnect.CCBack.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.ElementCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String religion;
	private String sexo;

	@ElementCollection(targetClass=String.class)
	private List<String> carreras_seleccionadas;

    @JsonIgnore
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


	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<String> getCarreras_seleccionadas() {
		return this.carreras_seleccionadas;
	}

	public void setCarreras_seleccionadas(List<String> carreras_seleccionadas) {
		this.carreras_seleccionadas = carreras_seleccionadas;
	}


	public InformacionUsuario(String identidadGenero, String raza, String lugarOrigen, Date fechaNacimiento, String religion, String sexo, List<String> carreras_seleccionadas, UsuarioGeneral usuario) {
		this.identidadGenero = identidadGenero;
		this.raza = raza;
		this.lugarOrigen = lugarOrigen;
		this.fechaNacimiento = fechaNacimiento;
		this.religion = religion;
		this.sexo = sexo;
		this.carreras_seleccionadas = carreras_seleccionadas;
		this.usuario = usuario;
	}


}
