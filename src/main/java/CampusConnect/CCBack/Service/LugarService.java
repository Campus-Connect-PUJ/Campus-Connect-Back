package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Repository.LugarRepository;

@RestController
@RequestMapping("/lugar")
class LugarService {
    @Autowired
    private LugarRepository repository;

    @GetMapping("all")
    public Iterable<Lugar> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Lugar findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Lugar create(@RequestBody final Lugar dato) {
        Lugar c = new Lugar();
        c.setNombre(dato.getNombre());
        c.setUbicacion(dato.getUbicacion());
        return repository.save(c);
    }

}
