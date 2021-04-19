package CampusConnect.CCBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Service.RegimenAlimenticioService;

@RestController
@RequestMapping("/regimen_alimenticio")
class RegimenAlimenticioController {

    @Autowired
    private RegimenAlimenticioService repository;

    @GetMapping("all")
    public Iterable<RegimenAlimenticio> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public RegimenAlimenticio findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("{id}/restaurantes")
    public List<Restaurante> conseguirRestaurantesPorRegimen(@PathVariable("id") Long id) {
        return repository.findById(id).getRestaurantes();
    }

    @GetMapping("{id}/usuarios")
    public List<RegimenAlimenticioUsuario> conseguirUsuariosPorRegimen(@PathVariable("id") Long id) {
        return repository.findById(id).getUsuarios();
    }

    @PostMapping
    public RegimenAlimenticio create(@RequestBody final RegimenAlimenticio dato) {
        return repository.create(dato);
    }

}
