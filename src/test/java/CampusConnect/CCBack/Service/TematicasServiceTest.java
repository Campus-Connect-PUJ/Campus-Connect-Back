package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.TematicaRepository;

@Service
public class TematicasService {

    @Autowired
    private TematicaRepository repository;

    public Iterable<Tematica> findAll() {
        return GenericService.findAll(repository);
    }

    public Tematica findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Tematica create(final Tematica dato) {
        Tematica c = new Tematica();
        c.setNombre(dato.getNombre());
        return GenericService.create(repository, c);
    }

}
