package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Carrera;

import CampusConnect.CCBack.Repository.CarreraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CarreraService {
    @Autowired
    private CarreraRepository repository;

    @GetMapping("/carreras")
    public Iterable<Carrera> findAllCarreras() {
        return repository.findAll();
    }

    @GetMapping("/carrera/{id}")
    public Carrera findCarreraById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }
}
