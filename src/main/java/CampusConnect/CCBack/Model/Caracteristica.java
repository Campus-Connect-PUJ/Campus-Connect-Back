package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany
    @JoinTable (
        name = "CaracteristicasUsuario",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<UsuarioGeneral> usuarios;

    @ManyToMany
    @JoinTable (
        name = "CaracteristicasEvento",
        joinColumns = @JoinColumn(name = "idCaracteristica"),
        inverseJoinColumns = @JoinColumn(name = "idEvento"))
    private List<Eventos> eventos;

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
}
