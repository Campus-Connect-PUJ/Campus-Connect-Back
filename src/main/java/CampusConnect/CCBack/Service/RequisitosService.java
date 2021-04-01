package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Repository.RequisitoRepository;

@RestController
@RequestMapping("/requisito")
class RequisitoService {
    @Autowired
    private RequisitoRepository repository;

    @GetMapping("all")
    public Iterable<Requisito> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Requisito findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Requisito create(@RequestBody final Requisito dato) {
        Requisito c = new Requisito();
        c.setNombre(dato.getNombre());
        return repository.save(c);
    }

}
