package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.RestauranteRepository;

@RestController
class RestaurantesService {
    @Autowired
    private RestauranteRepository repository;

    @GetMapping("/restaurantes")
    public Iterable<Restaurante> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/restaurante/{id}")
    public Restaurante findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/restaurante/{id}/tipos")
    public List<TipoRestaurante> conseguirTiposRestaurante(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposRestaurante();
    }

    @GetMapping("/restaurante/{id}/regimenes_alimenticios")
    public List<RegimenAlimenticio> conseguirRegimenesAlimenticiosRestaurante(
        @PathVariable("id") Long id) {
        return repository.findById(id).get().getRegimenesAlimenticios();
    }
}
