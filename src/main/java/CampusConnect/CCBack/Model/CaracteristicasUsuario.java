package CampusConnect.CCBack.Model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CaracteristicasUsuario {

    private String identidadGenero; // que tipo de dato seria esto?
    private String perfilContexto; // que es esto?
    private String raza; // esto si seria un string?
    private String lugarOrigen;
    private Date fechaNacimiento;

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
}
