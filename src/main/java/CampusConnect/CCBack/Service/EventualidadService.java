package CampusConnect.CCBack.Service;

import static java.util.stream.Collectors.toCollection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

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
        e.setTipo(dato.getTipo());
        e.setDescripcion(dato.getDescripcion());

        e.setFecha(LocalDate.now());

        return repository.save(e);
    }

    public Iterable<Eventualidad> findCercano(Double lat, Double lon, int cantidad) {
        return StreamSupport
            .stream(this.findAll().spliterator(), false)
			.filter(eventualidad -> eventualidad.cercano(lat, lon))
			.limit(cantidad)
            .collect(toCollection(ArrayList::new));
    }

}
