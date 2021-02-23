package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class UsuarioGeneral {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String correo;

    private String carrera;
    private Integer semestre;

    @ManyToMany
    @JoinTable (
        name = "TipoAprendizajeUsuario",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idTipoAprendizaje"))
    private List<TipoAprendizaje> estiloAprendizaje;

    private CaracteristicasUsuario caracteristicas;

    public CaracteristicasUsuario getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(final CaracteristicasUsuario caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(final String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
    public String getCarrera(){
        return this.carrera;
    }
    public Integer getSemestre(){
        return this.semestre;
    }

    public void setCarrera(final String carrera) {
        this.carrera = carrera;
    }
    public void setSemestre(final Integer semestre) {
        this.semestre = semestre;
    }
	public List<TipoAprendizaje> getEstiloAprendizaje() {
		return estiloAprendizaje;
	}
	public void setEstiloAprendizaje(List<TipoAprendizaje> estiloAprendizaje) {
		this.estiloAprendizaje = estiloAprendizaje;
	}
}
