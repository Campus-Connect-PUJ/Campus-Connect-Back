package CampusConnect.CCBack.Controllers;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.TematicaRepository;
import CampusConnect.CCBack.Service.TematicasService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tematica")
class TematicasController {

    @Autowired
    private TematicasService repository;

    @GetMapping("all")
    public Iterable<Tematica> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Tematica findById(@PathVariable("id") final Long id) {
        return repository.findById(id);
    }

    @GetMapping("{id}/grupos_estudiantiles")
    public List<GrupoEstudiantil> gruposDeTematica(@PathVariable("id") final Long id) {
        return repository.findById(id).getGruposEstudiantiles();
    }

    @GetMapping("{id}/caracteristicas")
    public List<Caracteristica> caracteristicasDeTematica(@PathVariable("id") final Long id) {
        return repository.findById(id).getCaracteristicas();
    }

    @PostMapping
    public Tematica create(@RequestBody final Tematica dato) {
        return repository.create(dato);
    }

}
