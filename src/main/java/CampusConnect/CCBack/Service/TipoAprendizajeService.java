package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Repository.TipoAprendizajeRepository;

@Service
public class TipoAprendizajeService {

    @Autowired
    private TipoAprendizajeRepository repository;

    public Iterable<TipoAprendizaje> findAll() {
        return GenericService.findAll(repository);
    }

    public TipoAprendizaje findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public TipoAprendizaje create(final TipoAprendizaje dato) {
        TipoAprendizaje c = new TipoAprendizaje();
        c.setDescripcion(dato.getDescripcion());
        return GenericService.create(repository, c);
    }

}
