package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facultad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "FacultadesGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idFacultad"),
        inverseJoinColumns = @JoinColumn(name = "idGrupoEstudiantil"))
    private List<GrupoEstudiantil> gruposEstudiantiles;

    @JsonIgnore
    @OneToMany(mappedBy = "facultad")
    private List<Carrera> carreras;

    public Facultad() {
        this.gruposEstudiantiles = new ArrayList<>();
        this.carreras = new ArrayList<>();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

}
