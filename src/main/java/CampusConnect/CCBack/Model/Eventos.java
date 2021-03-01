package CampusConnect.CCBack.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Eventos {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private LocalTime fecha;

    private String descripcion;

    @ManyToMany(mappedBy = "eventos")
    private List<Caracteristica> caracteristicas;

	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
