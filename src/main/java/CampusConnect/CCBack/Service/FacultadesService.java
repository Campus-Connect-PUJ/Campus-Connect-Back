package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Repository.FacultadRepository;

@RestController
@RequestMapping("/facultad")
class FacultadesService {
    @Autowired
    private FacultadRepository repository;

    @GetMapping("all")
    public Iterable<Facultad> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Facultad findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/grupos_estudiantiles")
    public List<GrupoEstudiantil> getUsuarios(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getGruposEstudiantiles();
    }

    @PostMapping
    public Facultad create(@RequestBody final Facultad dato) {
        Facultad c = new Facultad();
        c.setNombre(dato.getNombre());
        return repository.save(c);
    }

}
