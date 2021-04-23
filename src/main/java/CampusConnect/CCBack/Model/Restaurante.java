package CampusConnect.CCBack.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private float calificacion;

    // representa el tiempo en minutos promedio para realizar una entrega
    private float tiempoEntrega;

    private String franquicia;

    private float precioMax;

    private float precioMin;

    // descripcion breve de la ubicacion en la que se encuentra el restaurante
    private String descripcionLugar;

    private String ambientacion;

    @ManyToOne
    @JoinColumn(name="idLugar")
    private Lugar lugar;

    // @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "TiposRestaurante",
        joinColumns = @JoinColumn(name = "idRestaurante"),
        inverseJoinColumns = @JoinColumn(name = "idTipoRestaurante"))
    private List<TipoRestaurante> tiposRestaurante;

    // @JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "TiposComidaRestaurante",
        joinColumns = @JoinColumn(name = "idRestaurante"),
        inverseJoinColumns = @JoinColumn(name = "idTipoComida"))
    private List<TipoComida> tiposComida;

    //@JsonIgnore
    @ManyToMany
    @JoinTable (
        name = "RegimenesAlimenticiosRestaurante",
        joinColumns = @JoinColumn(name = "idRestaurante"),
        inverseJoinColumns = @JoinColumn(name = "idRegimenAlimenticio"))
    private List<RegimenAlimenticio> regimenesAlimenticios;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<ResenhaRestaurante> resenhas;

	public Restaurante() {
        this.calificacion = 0;
        this.resenhas = new ArrayList<>();
        this.regimenesAlimenticios = new ArrayList<>();
        this.tiposComida = new ArrayList<>();
        this.tiposRestaurante = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getAmbientacion() {
		return ambientacion;
	}

	public void setAmbientacion(String ambientacion) {
		this.ambientacion = ambientacion;
	}

	public String getDescripcionLugar() {
		return descripcionLugar;
	}

	public void setDescripcionLugar(String descripcionLugar) {
		this.descripcionLugar = descripcionLugar;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public float getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(float tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}

	public float getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(float precioMin) {
		this.precioMin = precioMin;
	}

	public float getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(float precioMax) {
		this.precioMax = precioMax;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    public Long getId() {
        return this.id;
    }

	public List<TipoRestaurante> getTiposRestaurante() {
		return tiposRestaurante;
	}

	public void setTiposRestaurante(List<TipoRestaurante> tiposRestaurante) {
		this.tiposRestaurante = tiposRestaurante;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public List<RegimenAlimenticio> getRegimenesAlimenticios() {
		return regimenesAlimenticios;
	}

	public void setRegimenesAlimenticios(List<RegimenAlimenticio> regimenesAlimenticios) {
		this.regimenesAlimenticios = regimenesAlimenticios;
	}

	public List<TipoComida> getTiposComida() {
		return tiposComida;
	}

	public void setTiposComida(List<TipoComida> tiposComida) {
		this.tiposComida = tiposComida;
	}

	public List<ResenhaRestaurante> getResenhas() {
		return resenhas;
	}

	public void setResenhas(List<ResenhaRestaurante> resenhas) {
		this.resenhas = resenhas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void agregarTipoRestaurante(TipoRestaurante c) {
        this.tiposRestaurante.add(c);
	}

	public void agregarTipoComida(TipoComida c) {
        this.tiposComida.add(c);
	}

	public void agregarRegimenAlimenticio(RegimenAlimenticio c) {
        this.regimenesAlimenticios.add(c);
	}

}
