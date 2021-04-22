package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.Actividad;

import org.springframework.data.repository.CrudRepository;

public interface ActividadRepository
    extends CrudRepository<Actividad, Long> {

    public Actividad findByNombre(String nombre);

}
