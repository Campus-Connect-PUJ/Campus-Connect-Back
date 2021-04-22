package CampusConnect.CCBack.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ResenhaRestaurante {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private float estrellas;

    @ManyToOne
    @JoinColumn(name="idRestaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioGeneral usuario;

	public float getEstrellas() {
		return estrellas;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public void setEstrellas(float estrellas) {
		this.estrellas = estrellas;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

}
