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
        return repository.findAll();
    }

    public Facultad findById(final Long id) {
        return repository.findById(id).get();
    }

    public Facultad create(final Facultad dato) {
        Facultad f = new Facultad();
        f.setNombre(dato.getNombre());
        return repository.save(f);
    }

}
