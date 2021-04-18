package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.Hobby;

import org.springframework.data.repository.CrudRepository;

public interface HobbyRepository
    extends CrudRepository<Hobby, Long> {

    public Hobby findByNombre(String nombre);

}
