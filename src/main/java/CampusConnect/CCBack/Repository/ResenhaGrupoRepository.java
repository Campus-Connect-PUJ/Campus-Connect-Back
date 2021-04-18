package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;

import org.springframework.data.repository.CrudRepository;

public interface ResenhaGrupoRepository
    extends CrudRepository<ResenhaGrupoEstudiantil, Long> {

}
