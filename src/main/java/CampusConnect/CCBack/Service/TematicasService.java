package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.TematicaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TematicasService {
    @Autowired
    private TematicaRepository repository;

    @GetMapping("/tematicas")
    public Iterable<Tematica> findAll() {
        return repository.findAll();
    }

    @GetMapping("/tematica/{id}")
    public Tematica findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/tematica/{id}/grupos_estudiantiles")
    public List<GrupoEstudiantil> gruposDeTematica(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getGruposEstudiantiles();
    }
}
