package CampusConnect.CCBack.Wrappers;

import java.time.LocalDate;
import java.util.List;

public class WrapperInformacionUsuario {

    private LocalDate fechaNacimiento;
    private List<Long> carreras;
    private String religion;
    private Boolean local;
    private String grupoEtnico;
    private String sexo;
    private String genero;
	public String getSexo() {
		return sexo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<Long> getCarreras() {
		return carreras;
	}
	public void setCarreras(List<Long> carreras) {
		this.carreras = carreras;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public Boolean getLocal() {
		return local;
	}
	public void setLocal(Boolean local) {
		this.local = local;
	}
	public String getGrupoEtnico() {
		return grupoEtnico;
	}
	public void setGrupoEtnico(String grupoEtnico) {
		this.grupoEtnico = grupoEtnico;
	}


}
