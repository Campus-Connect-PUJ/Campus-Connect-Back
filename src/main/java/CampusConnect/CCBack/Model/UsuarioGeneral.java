package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class UsuarioGeneral {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String correo;

    private String carrera;
    private Integer semestre;

    @OneToOne
    @JoinColumn(name = "caracteristicaId")
    private CaracteristicasUsuario caracteristicasUsuario;

    // relaciones muchos a muchos  ---------------------

    @ManyToMany
    @JoinTable (
        name = "TipoAprendizajeUsuario",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idTipoAprendizaje"))
    private List<TipoAprendizaje> estilosAprendizaje;

    @ManyToMany(mappedBy = "usuarios")
    private List<UsuarioCAE> rolesCAE;

    @ManyToMany(mappedBy = "monitores")
    private List<Asignatura> monitorDe;

    @ManyToMany(mappedBy = "usuarios")
    private List<Caracteristica> caracteristicas;

    @ManyToMany(mappedBy = "usuarios")
    private List<RolAdministrador> rolesAdministrador;

    @ManyToMany(mappedBy = "usuarios")
    private List<Carrera> carrerasUsuario;

    public UsuarioGeneral() { }

    public List<UsuarioCAE> getRolesCAE() {
		return rolesCAE;
	}

	public void setRolesCAE(List<UsuarioCAE> rolesCAE) {
		this.rolesCAE = rolesCAE;
	}

	public UsuarioGeneral(String nombre, String correo, String carrera,
                          Integer semestre, List<TipoAprendizaje> estiloAprendizaje,
                          CaracteristicasUsuario caracteristicas) {
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.semestre = semestre;
        this.estilosAprendizaje = estiloAprendizaje;
        this.caracteristicasUsuario = caracteristicas;
	}

    public CaracteristicasUsuario getCaracteristicas() {
		return caracteristicasUsuario;
	}
	public void setCaracteristicas(final CaracteristicasUsuario caracteristicas) {
		this.caracteristicasUsuario = caracteristicas;
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
		return estilosAprendizaje;
	}
	public void setEstiloAprendizaje(List<TipoAprendizaje> estiloAprendizaje) {
		this.estilosAprendizaje = estiloAprendizaje;
	}

	public CaracteristicasUsuario getCaracteristicasUsuario() {
		return caracteristicasUsuario;
	}

	public void setCaracteristicasUsuario(CaracteristicasUsuario caracteristicasUsuario) {
		this.caracteristicasUsuario = caracteristicasUsuario;
	}

	public List<Asignatura> getMonitorDe() {
		return monitorDe;
	}

	public void setMonitorDe(List<Asignatura> monitorDe) {
		this.monitorDe = monitorDe;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public List<RolAdministrador> getRolesAdministrador() {
		return rolesAdministrador;
	}

	public void setRolesAdministrador(List<RolAdministrador> rolesAdministrador) {
		this.rolesAdministrador = rolesAdministrador;
	}
}
