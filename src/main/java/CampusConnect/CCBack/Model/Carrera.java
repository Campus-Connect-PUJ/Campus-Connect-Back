package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Carrera {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @JsonIgnore
    @ManyToMany(mappedBy = "carrerasUsuario")
    private List<UsuarioGeneral> usuarios;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    @Override
    public String toString() {
        return "{" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}
