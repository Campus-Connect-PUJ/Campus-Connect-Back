package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ResenhaRestauranteRepository;

@RestController
public class ResenhaRestauranteService {

    @Autowired
    private ResenhaRestauranteRepository repository;

    @Autowired
    private RestaurantesService rService;

    public Iterable<ResenhaRestaurante> findAll() {
        return repository.findAll();
    }

    public ResenhaRestaurante findById(Long id) {
        return repository.findById(id).get();
    }

    public ResenhaRestaurante create(
        final UsuarioGeneral ug,
        final ResenhaRestaurante foroData,
        final Long idRestaurante
    ) {
        ResenhaRestaurante rr = new ResenhaRestaurante();
        Restaurante restaurante = rService.findById(idRestaurante);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setRestaurante(restaurante);
        rr.setUsuario(ug);
        return repository.save(rr);
    }

}
