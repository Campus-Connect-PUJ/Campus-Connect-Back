package CampusConnect.CCBack.Controllers;

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
import CampusConnect.CCBack.Service.TipoRestauranteService;

@RestController
@RequestMapping("/tipo_restaurante")
class TipoRestauranteController {

    @Autowired
    private TipoRestauranteService trService;

    @GetMapping("all")
    public Iterable<TipoRestaurante> findAll() {
        return trService.findAll();
    }

    @GetMapping("{id}")
    public TipoRestaurante findById(@PathVariable("id") Long id) {
        return trService.findById(id);
    }

    @GetMapping("{id}/restaurantes")
    public List<Restaurante> conseguirRestaurantesPorTipo(@PathVariable("id") Long id) {
        return trService.findById(id).getRestaurantes();
    }

    @PostMapping
    public TipoRestaurante create(@RequestBody final TipoRestaurante dato) {
        return trService.create(dato);
    }

}
