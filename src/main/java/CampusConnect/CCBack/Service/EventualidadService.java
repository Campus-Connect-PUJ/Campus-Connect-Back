package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import CampusConnect.CCBack.Model.Eventualidad;
import CampusConnect.CCBack.Repository.EventualidadRepository;

@Service
public class EventualidadService {

    @Autowired
    private EventualidadRepository repository;

    public Iterable<Eventualidad> findAll() {
        return repository.findAll();
    }

    public Eventualidad findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    public Eventualidad create(Eventualidad dato){
        Eventualidad e = new Eventualidad();

        e.setLongitud(dato.getLongitud());
        e.setLatitud(dato.getLatitud());
        e.setGravedad(dato.getGravedad());

        e.setDescripcion(dato.getDescripcion());

        return repository.save(e);
    }

}
