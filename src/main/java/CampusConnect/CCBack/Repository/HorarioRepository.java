package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Horario;

import org.springframework.data.repository.CrudRepository;

public interface HorarioRepository
    extends CrudRepository<Horario, Long> {

}
