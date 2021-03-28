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
public class Facultad {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "FacultadesGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idFacultad"),
        inverseJoinColumns = @JoinColumn(name = "idGrupoEstudiantil"))
    private List<GrupoEstudiantil> gruposEstudiantiles;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
