package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurante")
class RestaurantesService {
    @Autowired
    private RestauranteRepository repository;

    @GetMapping("all")
    public Iterable<Restaurante> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Restaurante findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/tipos")
    public List<TipoRestaurante> conseguirTiposRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposRestaurante();
    }

    @GetMapping("{id}/tipos_comida")
    public List<TipoComida> conseguirTiposComidaRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposComida();
    }

    @GetMapping("{id}/regimenes_alimenticios")
    public List<RegimenAlimenticio> conseguirRegimenesAlimenticiosRestaurante(
        @PathVariable("id") Long id) {
        return repository.findById(id).get().getRegimenesAlimenticios();
    }

    @GetMapping("{id}/resenhas")
    public List<ResenhaRestaurante> conseguirResenhasRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getResenhas();
    }

}
