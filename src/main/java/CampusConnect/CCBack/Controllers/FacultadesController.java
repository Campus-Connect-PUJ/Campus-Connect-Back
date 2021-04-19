package CampusConnect.CCBack.Controllers;

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
import CampusConnect.CCBack.Service.FacultadesService;

@RestController
@RequestMapping("/facultad")
class FacultadesController {

    @Autowired
    private FacultadesService fService;

    @GetMapping("all")
    public Iterable<Facultad> findAll() {
        return fService.findAll();
    }

    @GetMapping("{id}")
    public Facultad findById(@PathVariable("id") final Long id) {
        return fService.findById(id);
    }

    @GetMapping("{id}/grupos_estudiantiles")
    public List<GrupoEstudiantil> getUsuarios(@PathVariable("id") final Long id) {
        return fService.findById(id).getGruposEstudiantiles();
    }

    @PostMapping
    public Facultad create(@RequestBody final Facultad dato) {
        return fService.create(dato);
    }

}
