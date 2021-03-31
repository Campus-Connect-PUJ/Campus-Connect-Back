package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Repository.RegimenAlimenticioRepository;

@RestController
@RequestMapping("/regimen_alimenticio")
class RegimenAlimenticioService {

    @Autowired
    private RegimenAlimenticioRepository repository;

    @GetMapping("s")
    public Iterable<RegimenAlimenticio> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public RegimenAlimenticio findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/{id}/restaurantes")
    public List<Restaurante> conseguirRestaurantesPorRegimen(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRestaurantes();
    }

    @GetMapping("/{id}/usuarios")
    public List<RegimenAlimenticioUsuario> conseguirUsuariosPorRegimen(@PathVariable("id") Long id) {
        return repository.findById(id).get().getUsuarios();
    }

}
