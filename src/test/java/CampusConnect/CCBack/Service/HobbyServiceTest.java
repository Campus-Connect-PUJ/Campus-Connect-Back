package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import CampusConnect.CCBack.Model.Hobby;
import CampusConnect.CCBack.Repository.HobbyRepository;

@Service
class HobbyService {

    @Autowired
    private HobbyRepository repository;

    public Iterable<Hobby> findAll() {
        return GenericService.findAll(repository);
    }

    @GetMapping("{id}")
    public Hobby findById(@PathVariable("id") Long id) {
        return GenericService.findById(repository, id);
    }

    public Hobby findByName(String name) {
        try {
            return repository.findByNombre(name);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Hobby con nombre " + name + " no encontrado",
                exc);
        }
    }

    public Hobby crear(String name){
        Hobby hobby = new Hobby();
        hobby.setNombre(name);

        return GenericService.save(repository, hobby);
    }

}
