package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UsuarioCAE {

    @Id
    @GeneratedValue
    private Long id;

    private String rolCAE;

    @ManyToMany
    @JoinTable (
        name = "RolesCAE",
        joinColumns = @JoinColumn(name = "idRolCAE"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuarios;

	public String getRolCAE() {
		return rolCAE;
	}

	public List<UsuarioGeneral> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioGeneral> usuarios) {
		this.usuarios = usuarios;
	}

	public void setRolCAE(String rolCAE) {
		this.rolCAE = rolCAE;
	}

}
