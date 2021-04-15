package CampusConnect.CCBack.Model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rol {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<UsuarioGeneral> usuarioGeneral;

    private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UsuarioGeneral> getUsuarioLogin() {
		return usuarioGeneral;
	}

	public void setUsuarioLogin(List<UsuarioGeneral> usuarioLogin) {
		this.usuarioGeneral = usuarioLogin;
	}

}
