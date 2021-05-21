package CampusConnect.CCBack.Service;

import static java.util.stream.Collectors.toCollection;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Eventualidad;
import CampusConnect.CCBack.Repository.EventualidadRepository;

@Service
public class EventualidadService {

    @Autowired
    private EventualidadRepository repository;

    public Iterable<Eventualidad> findAll() {
        return GenericService.findAll(repository);
    }

    public Eventualidad findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public void delete(Eventualidad e) {
        GenericService.delete(repository, e);
    }

    // borrar todas las eventualidades que sean mas viejas que (hoy - numeroDias)
    public void deleteEventualidadesViejas(int numeroDias) {
        LocalDate today = LocalDate.now();
        LocalDate daysAgo = today.minus(Period.ofDays(numeroDias));
        repository.deleteByFechaBefore(daysAgo);
    }

    // borrar todas las eventualidades que no tengan tipo incluido
    // dentro de la lista except, que sean mas viejas que (hoy - numeroDias)
    public void deleteEventualidadesViejas(int numeroDias, int... except) {
        LocalDate today = LocalDate.now();
        LocalDate daysAgo = today.minus(Period.ofDays(numeroDias));
        List<Eventualidad> le = repository.getByFechaBefore(daysAgo);

        IntStream intStream = Arrays.stream(except);

        for (Eventualidad e : le) {
            // no borrar la eventualidad en caso de no estar dentro de except
            if (!intStream.anyMatch(i -> i == e.getTipo())) {
                this.delete(e);
            }
        }
    }

    public Eventualidad create(Eventualidad dato){
        Eventualidad e = new Eventualidad();

        e.setLongitud(dato.getLongitud());
        e.setLatitud(dato.getLatitud());
        e.setTipo(dato.getTipo());
        e.setDescripcion(dato.getDescripcion());

        e.setFecha(LocalDate.now());

        return GenericService.save(repository, e);
    }

    public Iterable<Eventualidad> findCercano(Double lat, Double lon, int cantidad) {
        return StreamSupport
            .stream(this.findAll().spliterator(), false)
			.filter(eventualidad -> eventualidad.cercano(lat, lon))
			.limit(cantidad)
            .collect(toCollection(ArrayList::new));
    }

}
