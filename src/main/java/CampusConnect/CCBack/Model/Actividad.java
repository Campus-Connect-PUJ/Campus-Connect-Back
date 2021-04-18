package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Actividad {

	@Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany
    @JoinTable(
        name = "ActividadesInteresUsuario",
        joinColumns = @JoinColumn(name = "idActividad"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuarios;

    @ManyToMany
    @JoinTable(
        name = "ActividadesGrupoEstudiantil",
        joinColumns = @JoinColumn(name = "idActividad"),
        inverseJoinColumns = @JoinColumn(name = "idGrupoEstudiantil"))
    private List<GrupoEstudiantil> gruposEstudiantiles;

	public List<GrupoEstudiantil> getGruposEstudiantiles() {
		return gruposEstudiantiles;
	}

	public void setGruposEstudiantiles(List<GrupoEstudiantil> gruposEstudiantiles) {
		this.gruposEstudiantiles = gruposEstudiantiles;
	}

	public List<UsuarioGeneral> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioGeneral> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
