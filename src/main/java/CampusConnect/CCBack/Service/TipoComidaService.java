package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Repository.TipoComidaRepository;

@Service
public class TipoComidaService {

    @Autowired
    private TipoComidaRepository repository;

    public Iterable<TipoComida> findAll() {
        return GenericService.findAll(repository);
    }

    public TipoComida findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public TipoComida create(final TipoComida dato) {
        TipoComida c = new TipoComida();
        c.setTipo(dato.getTipo());
        return GenericService.create(repository, c);
    }

}
