package CampusConnect.CCBack.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue
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

    @ManyToOne
    @JoinColumn(name="idLugar")
    private Lugar lugar;

    @ManyToMany
    @JoinTable (
        name = "TiposRestaurante",
        joinColumns = @JoinColumn(name = "idRestaurante"),
        inverseJoinColumns = @JoinColumn(name = "idTipoRestaurante"))
    private List<TipoRestaurante> tiposRestaurante;

    @ManyToMany
    @JoinTable (
        name = "RegimenesAlimenticiosRestaurante",
        joinColumns = @JoinColumn(name = "idRestaurante"),
        inverseJoinColumns = @JoinColumn(name = "idRegimenAlimenticio"))
    private List<RegimenAlimenticio> regimenesAlimenticios;

	public String getNombre() {
		return nombre;
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

}
