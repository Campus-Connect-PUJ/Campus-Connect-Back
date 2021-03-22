package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UsuarioGeneral {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String correo;

    private Integer semestre;

    @OneToOne(mappedBy = "usuario",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private InformacionUsuario informacionUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Tip> tips;

    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    @OneToMany(mappedBy = "usuario")
    private List<RespuestaPost> respuestasPosts;

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

	public UsuarioGeneral(String nombre, String correo,
                          Integer semestre, List<TipoAprendizaje> estiloAprendizaje,
                          InformacionUsuario caracteristicas) {
        this.nombre = nombre;
        this.correo = correo;
        this.semestre = semestre;
        this.estilosAprendizaje = estiloAprendizaje;
        this.informacionUsuario = caracteristicas;
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
    public Integer getSemestre(){
        return this.semestre;
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

	public InformacionUsuario getCaracteristicasUsuario() {
		return informacionUsuario;
	}

	public void setCaracteristicasUsuario(InformacionUsuario caracteristicasUsuario) {
		this.informacionUsuario = caracteristicasUsuario;
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

	public InformacionUsuario getInformacionUsuario() {
		return informacionUsuario;
	}

	public void setInformacionUsuario(InformacionUsuario informacionUsuario) {
		this.informacionUsuario = informacionUsuario;
	}

	public List<TipoAprendizaje> getEstilosAprendizaje() {
		return estilosAprendizaje;
	}

	public void setEstilosAprendizaje(List<TipoAprendizaje> estilosAprendizaje) {
		this.estilosAprendizaje = estilosAprendizaje;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public List<Carrera> getCarrerasUsuario() {
		return carrerasUsuario;
	}

	public void setCarrerasUsuario(List<Carrera> carrerasUsuario) {
		this.carrerasUsuario = carrerasUsuario;
	}

	public List<Tip> getTips() {
		return tips;
	}

	public void setTips(List<Tip> tips) {
		this.tips = tips;
	}
}
