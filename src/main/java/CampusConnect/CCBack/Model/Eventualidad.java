package CampusConnect.CCBack.Model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Eventualidad {

	// representacion de 1 metro de distancia para coordenadas
    // TODO: encontrar este valor, de momento me lo estoy inventando
    private static final double COORDINATE_METER = 0.0001;
    private static final double DISTANCIA_MAX = 50 * COORDINATE_METER;

    @GeneratedValue
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    private int tipo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Double longitud;

    @Column(nullable = false)
	private Double latitud;

    public Double distancia(Eventualidad e) {
        return distancia(e.getLongitud(), e.getLatitud());
    }

	public Double distancia(Double lat, Double lon) {
        Double distLong  = Math.pow((this.longitud - lon), 2);
        Double distLat   = Math.pow((this.latitud - lat), 2);
        Double distancia = Math.sqrt(distLong + distLat);
        return distancia;
    }

    public Boolean cercano(Eventualidad e){
        return this.distancia(e) < DISTANCIA_MAX;
    }

    public Boolean cercano(Double lat, Double lon){
        return this.distancia(lat, lon) < DISTANCIA_MAX;
    }

    public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
