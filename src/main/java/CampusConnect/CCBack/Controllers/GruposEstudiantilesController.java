package CampusConnect.CCBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Service.GruposEstudiantilesService;
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;

@RestController
@RequestMapping("/grupo_estudiantil")
class GruposEstudiantilesController {

    @Autowired
    private GruposEstudiantilesService geService;

    @GetMapping("all")
    public Iterable<GrupoEstudiantil> findAll() {
        return geService.findAll();
    }

    @GetMapping("{id}")
    public GrupoEstudiantil findById(@PathVariable("id") Long id) {
        return geService.findById(id);
    }

    @GetMapping("{id}/caracteristicas")
    public List<Caracteristica> conseguirCaracteristicasGrupo(@PathVariable("id") Long id) {
        return geService.findById(id).getCaracteristicas();
    }

    @PostMapping
    public GrupoEstudiantil create(@RequestBody final WrapperGrupoEstudiantil dato) {
        return geService.create(dato);
    }

    @PostMapping("{idge}/requisito/{idr}")
    void agregarRequisito(@PathVariable("idge") Long idge, @PathVariable("idr") Long idr) {
        geService.agregarRequisito(idge, idr);
    }
}
