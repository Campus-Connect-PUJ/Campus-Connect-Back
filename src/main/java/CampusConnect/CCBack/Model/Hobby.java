package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Hobby {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "hobbies")
    private List<InformacionUsuario> usuarios;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InformacionUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<InformacionUsuario> usuarios) {
		this.usuarios = usuarios;
	}

}
