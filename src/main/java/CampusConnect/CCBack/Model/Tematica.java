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
public class Tematica {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "TematicasGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idTematica"),
        inverseJoinColumns = @JoinColumn(name = "idGrupoEstudiantil"))
    private List<GrupoEstudiantil> gruposEstudiantiles;

    @JsonIgnore
    @ManyToMany(mappedBy = "tematicas")
    private List<Caracteristica> caracteristicas;

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

}
