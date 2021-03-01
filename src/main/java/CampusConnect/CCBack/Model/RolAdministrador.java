package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class RolAdministrador {

    @Id
    @GeneratedValue
    private Long id;

    // @JsonIgnore // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    @ManyToMany
    @JoinTable (
        name = "RolesAdministrador",
        joinColumns = @JoinColumn(name = "idRolAdministrador"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuarios;

	public List<UsuarioGeneral> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioGeneral> usuarios) {
		this.usuarios = usuarios;
	}
}
