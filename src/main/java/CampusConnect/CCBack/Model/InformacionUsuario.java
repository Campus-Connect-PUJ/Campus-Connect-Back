package CampusConnect.CCBack.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

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

    private String raza;

    private String lugarOrigen;

    private LocalDate fechaNacimiento;

    private String actividadInteres;

    private String hobbies;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioGeneral usuario;

	public String getIdentidadGenero() {
		return identidadGenero;
    }

    public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

    public String getRaza() {
		return raza;
	}

    public void setRaza(String raza) {
		this.raza = raza;
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

	public String getActividadInteres() {
		return actividadInteres;
	}

	public void setActividadInteres(String actividadInteres) {
		this.actividadInteres = actividadInteres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
