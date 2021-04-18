package CampusConnect.CCBack.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class InformacionUsuario {

    @Id
    @GeneratedValue
    private Long id;

    private String identidadGenero;

    private String identidadSexo;

    private String identidadEtnica;

    private String identidadReligiosa;

    private Boolean lugarOrigen;

    private LocalDate fechaNacimiento;

    @ManyToMany
    @JoinTable (
        name = "HobbiesUsuario",
        joinColumns = @JoinColumn(name = "idInfoUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idHobby"))
    private List<Hobby> hobbies;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioGeneral usuario;

	public InformacionUsuario() {
		this.hobbies = new ArrayList<>();
	}

	public String getIdentidadGenero() {
		return identidadGenero;
    }

    public String getIdentidadSexo() {
		return identidadSexo;
	}

	public void setIdentidadSexo(String identidadSexo) {
		this.identidadSexo = identidadSexo;
	}

	public String getIdentidadReligiosa() {
		return identidadReligiosa;
	}

	public void setIdentidadReligiosa(String identidadReligiosa) {
		this.identidadReligiosa = identidadReligiosa;
	}

	public Boolean getLugarOrigen() {
		return lugarOrigen;
	}

	public void setLugarOrigen(Boolean lugarOrigen) {
		this.lugarOrigen = lugarOrigen;
	}

	public String getIdentidadEtnica() {
		return identidadEtnica;
	}

	public void setIdentidadEtnica(String identidadEtnica) {
		this.identidadEtnica = identidadEtnica;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

    public void setIdentidadGenero(String identidadGenero) {
		this.identidadGenero = identidadGenero;
	}

    public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
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

    public void agregarHobby(Hobby h) {
        this.hobbies.add(h);
    }

}
