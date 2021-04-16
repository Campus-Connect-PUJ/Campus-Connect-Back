package CampusConnect.CCBack.Model;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UsuarioGeneral {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellido;
    private Integer semestre;

///////////////////////////////////////////////////////////////////////////////
//                                inicio login                               //
///////////////////////////////////////////////////////////////////////////////

    private String email;
	private String password;

    private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;

	private short rol; // para no tener que guardarlo en una tabla aparte

///////////////////////////////////////////////////////////////////////////////
//                                final login                                //
///////////////////////////////////////////////////////////////////////////////

    @JsonIgnore
    @OneToOne(mappedBy = "usuario",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private InformacionUsuario informacionUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Tip> tips;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Foro> foros;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<RespuestaForo> respuestasForo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<ResenhaGrupoEstudiantil> resenhaGruposEstudiatiles;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<ResenhaRestaurante> resenhaRestaurantes;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private RegimenAlimenticioUsuario regimenAlimenticio;

    // relaciones muchos a muchos  ---------------------

    @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "TipoAprendizajeUsuario",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idTipoAprendizaje"))
    private List<TipoAprendizaje> estilosAprendizaje;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<UsuarioCAE> rolesCAE;

    @JsonIgnore
    @ManyToMany(mappedBy = "monitores")
    private List<Asignatura> monitorDe;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Caracteristica> caracteristicas;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<RolAdministrador> rolesAdministrador;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Carrera> carrerasUsuario;

    @ManyToMany(mappedBy = "usuariosGustaron")
    private List<Tip> tipsGustados;

    @ManyToMany(mappedBy = "usuariosGustaron")
    private List<Tematica> tematicasGustan;

    public UsuarioGeneral(
        String email,
        String password,
        String nombre,
        String apellido
        ) {
		this.email = email;
		this.password = password;
        this.rol = Rol.USER; // tener el rol de usuario por default

        this.nombre = nombre;
        this.apellido = apellido;

        inicializar();
    }

    public UsuarioGeneral() {
        inicializar();
    }

    private void inicializar() {
        this.caracteristicas = new ArrayList<>();
        this.carrerasUsuario = new ArrayList<>();
        this.estilosAprendizaje = new ArrayList<>();
        this.foros = new ArrayList<>();
        this.monitorDe = new ArrayList<>();
        this.resenhaRestaurantes = new ArrayList<>();
        this.resenhaGruposEstudiatiles = new ArrayList<>();
        this.respuestasForo = new ArrayList<>();
        this.tipsGustados = new ArrayList<>();
        this.tips = new ArrayList<>();

        this.enabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        // this.regimenAlimenticio = new RegimenAlimenticioUsuario();
    }

    public List<UsuarioCAE> getRolesCAE() {
		return rolesCAE;
	}

	public void setRolesCAE(List<UsuarioCAE> rolesCAE) {
		this.rolesCAE = rolesCAE;
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

	public List<RespuestaForo> getRespuestasForo() {
		return respuestasForo;
	}

	public void setRespuestasForo(List<RespuestaForo> respuestasForo) {
		this.respuestasForo = respuestasForo;
	}

	public List<Foro> getForos() {
		return foros;
	}

	public void setForos(List<Foro> foros) {
		this.foros = foros;
	}

	public List<Tip> getTipsGustados() {
		return tipsGustados;
	}

	public void setTipsGustados(List<Tip> tipsGustados) {
		this.tipsGustados = tipsGustados;
	}

	public void agregarResenhaRestaurante(ResenhaRestaurante rr) {
        this.resenhaRestaurantes.add(rr);
	}

	public void agregarResenhaGrupoEstudiantil(ResenhaGrupoEstudiantil rr) {
        this.resenhaGruposEstudiatiles.add(rr);
	}

	public List<ResenhaGrupoEstudiantil> getResenhaGruposEstudiatiles() {
		return resenhaGruposEstudiatiles;
	}

	public void setResenhaGruposEstudiatiles(List<ResenhaGrupoEstudiantil> resenhaGruposEstudiatiles) {
		this.resenhaGruposEstudiatiles = resenhaGruposEstudiatiles;
	}

	public List<ResenhaRestaurante> getResenhaRestaurantes() {
		return resenhaRestaurantes;
	}

	public void setResenhaRestaurantes(List<ResenhaRestaurante> resenhaRestaurantes) {
		this.resenhaRestaurantes = resenhaRestaurantes;
	}

	public RegimenAlimenticioUsuario getRegimenAlimenticio() {
		return regimenAlimenticio;
	}

	public void setRegimenAlimenticio(RegimenAlimenticioUsuario regimenAlimenticio) {
		this.regimenAlimenticio = regimenAlimenticio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRol() {
		return Rol.string(this.rol);
	}

	public void setRol(short rol) {
		this.rol = rol;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

    public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    public void agregarCarrera(Carrera c) {
        this.carrerasUsuario.add(c);
    }

}
