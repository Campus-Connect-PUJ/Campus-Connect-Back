package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "CaracteristicasUsuario",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuarios;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "CaracteristicasEvento",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idEvento"))
    private List<Eventos> eventos;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "CaracteristicasGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idGrupoEstudiantil"))
    private List<GrupoEstudiantil> gruposEstudiantiles;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "CaracteristicasTematicas",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idTematica"))
    private List<Tematica> tematicas;

    public String getNombre() {
		return nombre;
	}

    public List<Eventos> getEventos() {
		return eventos;
	}

    public void setEventos(List<Eventos> eventos) {
		this.eventos = eventos;
	}

    public List<UsuarioGeneral> getUsuarios() {
		return usuarios;
	}

    public void setUsuarios(List<UsuarioGeneral> usuarios) {
		this.usuarios = usuarios;
	}

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public List<Tematica> getTematicas() {
		return tematicas;
	}

    public void setTematicas(List<Tematica> tematicas) {
		this.tematicas = tematicas;
	}

    public List<GrupoEstudiantil> getGruposEstudiantiles() {
		return gruposEstudiantiles;
	}

    public void setGruposEstudiantiles(List<GrupoEstudiantil> gruposEstudiantiles) {
		this.gruposEstudiantiles = gruposEstudiantiles;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void agregarGrupoEstudiantil(GrupoEstudiantil ge) {
        this.gruposEstudiantiles.add(ge);
    }

}
