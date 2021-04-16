package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.UsuarioGeneral;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioGeneralRepository
    extends CrudRepository<UsuarioGeneral, Long> {

    public UsuarioGeneral findByEmail(String email);

}
