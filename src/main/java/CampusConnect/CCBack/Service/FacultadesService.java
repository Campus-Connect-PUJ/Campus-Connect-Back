package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Repository.FacultadRepository;

@Service
public class FacultadesService {

    @Autowired
    private FacultadRepository repository;

    public Iterable<Facultad> findAll() {
        return GenericService.findAll(repository);
    }

    public Facultad findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Facultad create(final Facultad dato) {
        Facultad f = new Facultad();
        f.setNombre(dato.getNombre());
        return GenericService.save(repository, f);
    }

}
