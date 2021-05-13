package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.TipoRestaurante;
import CampusConnect.CCBack.Repository.TipoRestauranteRepository;

@Service
public class TipoRestauranteService {

    @Autowired
    private TipoRestauranteRepository repository;

    public Iterable<TipoRestaurante> findAll() {
        return GenericService.findAll(repository);
    }

    public TipoRestaurante findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public TipoRestaurante create(final TipoRestaurante dato) {
        TipoRestaurante c = new TipoRestaurante();
        c.setTipo(dato.getTipo());
        return GenericService.create(repository, c);
    }

}
