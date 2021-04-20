package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Repository.AsignaturaRepository;

@RestController
@RequestMapping("/asignatura")
class AsignaturaService {

    @Autowired
    private AsignaturaRepository repository;

    @GetMapping("all")
    public Iterable<Asignatura> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Asignatura findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Asignatura create(
        @RequestBody final Asignatura dato
    ) {
        Asignatura a = new Asignatura();
        a.setNombre(dato.getNombre());
        a.setDescripcion(dato.getNombre());
        return repository.save(a);
    }

    // @PostMapping("{id}")
    // public UsuarioMonitor create() {
    //     // TODO
    //     return repository.save(um);
    // }

}