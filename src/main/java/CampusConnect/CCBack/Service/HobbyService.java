package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import CampusConnect.CCBack.Model.Hobby;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.HobbyRepository;

@Service
class HobbyService {

    @Autowired
    private HobbyRepository repository;

    public Iterable<Hobby> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Hobby findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    public Hobby findByName(String name) {
        return repository.findByNombre(name);
    }

}
