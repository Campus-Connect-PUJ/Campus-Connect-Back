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
        return repository.findAll();
    }

    public TipoRestaurante findById(Long id) {
        return repository.findById(id).get();
    }

    public TipoRestaurante create(final TipoRestaurante dato) {
        TipoRestaurante c = new TipoRestaurante();
        c.setTipo(dato.getTipo());
        return repository.save(c);
    }

}
