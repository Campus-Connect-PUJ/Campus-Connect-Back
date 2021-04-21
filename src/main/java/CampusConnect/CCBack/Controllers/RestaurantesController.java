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
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Service.RestaurantesService;
import CampusConnect.CCBack.Wrappers.WrapperRestaurante;

@RestController
@RequestMapping("/restaurante")
class RestaurantesController {

    @Autowired
    private RestaurantesService rService;

    @GetMapping("all")
    public Iterable<Restaurante> findAll() {
        return rService.findAll();
    }

    @GetMapping("{id}")
    public Restaurante findById(@PathVariable("id") Long id) {
        return rService.findById(id);
    }

    @GetMapping("{id}/tipos")
    public List<TipoRestaurante> conseguirTiposRestaurante(@PathVariable("id") Long id) {
        return rService.findById(id).getTiposRestaurante();
    }

    @GetMapping("{id}/tipos_comida")
    public List<TipoComida> conseguirTiposComidaRestaurante(@PathVariable("id") Long id) {
        return rService.findById(id).getTiposComida();
    }

    @GetMapping("{id}/regimenes_alimenticios")
    public List<RegimenAlimenticio> conseguirRegimenesAlimenticiosRestaurante(
        @PathVariable("id") Long id) {
        return rService.findById(id).getRegimenesAlimenticios();
    }

    @GetMapping("{id}/resenhas")
    public List<ResenhaRestaurante> conseguirResenhasRestaurante(@PathVariable("id") Long id) {
        return rService.findById(id).getResenhas();
    }

    @PostMapping
    public Restaurante create(@RequestBody final WrapperRestaurante dato) {
        return rService.create(dato);
    }

}
