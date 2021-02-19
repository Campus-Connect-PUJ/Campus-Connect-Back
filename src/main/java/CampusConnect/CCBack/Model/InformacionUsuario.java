package CampusConnect.CCBack.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InformacionUsuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String correo;

    // TODO: definir como manejar contrasenhas
    private String password;

    private Date fechaNacimiento;
    private String identidadGenero; // que tipo de dato seria esto?
    private String carrera;
    private String perfilContexto; // que es esto?
    private Integer semestre;
    private String raza; // esto si seria un string?

    private String[] estiloAprendizaje;
    private String lugarOrigen;

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentidadGenero(){
        return this.identidadGenero;
    }
    public String getCarrera(){
        return this.carrera;
    }
    public String getPerfilContexto(){
        return this.perfilContexto;
    }
    public Integer getSemestre(){
        return this.semestre;
    }
    public String getRaza(){
        return this.raza;
    }
    public String[] getEstiloAprendizaje(){
        return this.estiloAprendizaje ;
    }
    public String getLugarOrigen(){
        return this.lugarOrigen;
    }

    public void setFechaNacimiento(final Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setIdentidadGenero(final String identidadGenero) {
        this.identidadGenero = identidadGenero;
    }
    public void setCarrera(final String carrera) {
        this.carrera = carrera;
    }
    public void setPerfilContexto(final String perfilContexto) {
        this.perfilContexto = perfilContexto;
    }
    public void setSemestre(final Integer semestre) {
        this.semestre = semestre;
    }
    public void setRaza(final String raza) {
        this.raza = raza;
    }
    public void setEstiloAprendizaje(final String[] estiloAprendizaje) {
        this.estiloAprendizaje = estiloAprendizaje;
    }
    public void setLugarOrigen(final String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }
}
