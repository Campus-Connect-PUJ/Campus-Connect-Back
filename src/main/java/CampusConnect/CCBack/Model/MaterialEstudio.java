package CampusConnect.CCBack.Model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MaterialEstudio {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    private File archivo;

    @ManyToOne
    @JoinColumn(name="idAsignatura")
    private Asignatura asignatura;

	public String getNombre() {
		return nombre;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
