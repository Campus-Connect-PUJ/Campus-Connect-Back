package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UsuarioGeneral implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private Integer semestre;

	private String ambientacion;

///////////////////////////////////////////////////////////////////////////////
//                                inicio login                               //
///////////////////////////////////////////////////////////////////////////////

    @Column(unique = true, nullable = false)
    private String email;

    // @JsonIgnore
    @Column(nullable = false)
	private String password;

    private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;

    @ElementCollection
	private List<Short> roles;

///////////////////////////////////////////////////////////////////////////////
//                                final login                                //
///////////////////////////////////////////////////////////////////////////////

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

    @OneToOne(mappedBy = "usuario",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private RegimenAlimenticioUsuario regimenAlimenticio;

    //@JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioMonitor> monitorDe;

    // relaciones muchos a muchos  ---------------------

    @ManyToMany
    @JoinTable (
        name = "TipoAprendizajeUsuario",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idTipoAprendizaje"))
    private List<TipoAprendizaje> estilosAprendizaje;

    @ManyToMany
    private List<Caracteristica> caracteristicas;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Carrera> carrerasUsuario;

	//@JsonIgnore
    @ManyToMany(mappedBy = "usuariosGustaron")
    private List<Tip> tipsGustados;

	//@JsonIgnore
	@ManyToMany(mappedBy = "usuariosNoGustaron")
    private List<Tip> tipsNoGustados;

    @ManyToMany(mappedBy = "usuariosGustaron")
    private List<Tematica> tematicasGustan;

    @ManyToMany(mappedBy = "usuarios")
    private List<Actividad> actividadInteres;

	@ManyToMany
	private List<TipoComida> comidaFavorita;

	@ManyToMany
    private List<UsuarioMonitor> monitoresVotados;

	public UsuarioGeneral(
        String email,
        String password,
        String nombre,
        String apellido
        ) {
		this.email = email;
		this.password = password;

        this.nombre = nombre;
        this.apellido = apellido;

        inicializar();

        this.roles.add(Rol.USER); // tener el rol de usuario por default
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
		this.tipsNoGustados = new ArrayList<>();
		this.monitoresVotados = new ArrayList<>();
        this.tips = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.actividadInteres = new ArrayList<>();
		this.comidaFavorita = new ArrayList<>();

        this.enabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        // this.regimenAlimenticio = new RegimenAlimenticioUsuario();
    }

    public List<Actividad> getActividadInteres() {
		return actividadInteres;
	}

	public void setActividadInteres(List<Actividad> actividadInteres) {
		this.actividadInteres = actividadInteres;
	}

    public void agregarActividadInteres(Actividad a) {
        this.actividadInteres.add(a);
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

	public List<UsuarioMonitor> getMonitorDe() {
		return monitorDe;
	}

	public void setMonitorDe(List<UsuarioMonitor> monitorDe) {
		this.monitorDe = monitorDe;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
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

	public List<Tip> getTipsNoGustados() {
		return tipsNoGustados;
	}

	public void setTipsNoGustados(List<Tip> tipsNoGustados) {
		this.tipsNoGustados = tipsNoGustados;
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

	public List<String> getRoles() {
        return this.roles.stream().map( v -> Rol.string(v)).collect(Collectors.toList());
		// return Rol.string(this.rol);
	}

	public void setRoles(List<Short> rol) {
		this.roles = rol;
	}

    public void setRol(Short rol) {
        this.roles.add(rol);
    }

    public void removeRol(Short rol) {
        this.roles.remove(rol);
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

	public void agregarTipGustaron(Tip tip){
		this.tipsGustados.add(tip);
	}

	public void agregarTipNoGustaron(Tip tip){
		this.tipsNoGustados.add(tip);
	}

    public void quitarUsuarioGustaron(Tip tip){
		this.tipsGustados.remove(tip);
    }

    public void quitarUsuarioNoGustaron(Tip tip){
		this.tipsNoGustados.remove(tip);
    }  

    public void agregarCaracteristica(Caracteristica c) {
        this.caracteristicas.add(c);
    }

	public List<Tematica> getTematicasGustan() {
		return tematicasGustan;
	}

	public void setTematicasGustan(List<Tematica> tematicasGustan) {
		this.tematicasGustan = tematicasGustan;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public void addMonitorDe(UsuarioMonitor monitoria){
		this.monitorDe.add(monitoria);
	}

	public void quitarMonitorDe(UsuarioMonitor monitoria){
		this.monitorDe.remove(monitoria);
	}

	public String getAmbientacion(){
		return this.ambientacion;
	}

	public void setAmbientacion(String ambientacion){
		this.ambientacion = ambientacion;
	}

	public List<TipoComida> getComidaFav(){
		return this.comidaFavorita;
	}

	public void setComidaFav(List<TipoComida> fav){
		this.comidaFavorita=fav;
	}

	public void agregarComida(TipoComida comida){
		this.comidaFavorita.add(comida);
	}

	public void agregarForo(Foro foro){
		this.foros.add(foro);
	}

	public void borrarRespuestaForo(RespuestaForo rf){
        this.respuestasForo.remove(rf);
    }

    public void reinicioPersoGrupos(){
        this.caracteristicas.clear();
        this.actividadInteres.clear();
    }

	public void reinicioPersoRestaurantes(){
		this.comidaFavorita.clear();
	}

	public List<UsuarioMonitor> getMonitoresVotaron() {
		return monitoresVotados;
	}

	public void setMonitoresVotaron(List<UsuarioMonitor> monitoresVotados) {
		this.monitoresVotados = monitoresVotados;
	}

	public void addMonitoresVotados(UsuarioMonitor monitor){
		this.monitoresVotados.add(monitor);
	}
}
