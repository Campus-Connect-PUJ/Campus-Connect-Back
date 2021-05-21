package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Repository.AsignaturaRepository;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository repository;

    public Iterable<Asignatura> findAll() {
        return GenericService.findAll(repository);
    }

    public Asignatura findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Asignatura create(final Asignatura dato) {
        Asignatura a = new Asignatura();
        a.setNombre(dato.getNombre());
        a.setDescripcion(dato.getNombre());
        return GenericService.save(repository, a);
    }

}
