package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Repository.RequisitoRepository;

@Service
public class RequisitoService {

    @Autowired
    private RequisitoRepository repository;

    public Iterable<Requisito> findAll() {
        return GenericService.findAll(repository);
    }

    public Requisito findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Requisito create(final Requisito dato) {
        Requisito c = new Requisito();
        c.setNombre(dato.getNombre());
        return GenericService.create(repository, c);
    }

}
