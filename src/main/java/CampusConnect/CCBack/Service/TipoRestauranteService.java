package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.TipoRestauranteRepository;

@RestController
@RequestMapping("/tipo_restaurante")
class TipoRestauranteService {

    @Autowired
    private TipoRestauranteRepository repository;

    @GetMapping("all")
    public Iterable<TipoRestaurante> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public TipoRestaurante findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/restaurantes")
    public List<Restaurante> conseguirRestaurantesPorTipo(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRestaurantes();
    }

    @PostMapping
    public TipoRestaurante create(@RequestBody final TipoRestaurante dato) {
        TipoRestaurante c = new TipoRestaurante();
        c.setTipo(dato.getTipo());
        return repository.save(c);
    }

}
