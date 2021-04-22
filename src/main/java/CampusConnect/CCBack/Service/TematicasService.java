package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Repository.TematicaRepository;

@Service
public class TematicasService {

    @Autowired
    private TematicaRepository repository;

    public Iterable<Tematica> findAll() {
        return repository.findAll();
    }

    public Tematica findById(final Long id) {
        return repository.findById(id).get();
    }

    public Tematica create(final Tematica dato) {
        Tematica c = new Tematica();
        c.setNombre(dato.getNombre());
        return repository.save(c);
    }

}
