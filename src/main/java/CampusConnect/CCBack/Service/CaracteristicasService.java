package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Repository.CaracteristicaRepository;

@Service
public class CaracteristicasService {

    @Autowired
    private CaracteristicaRepository repository;

    public Iterable<Caracteristica> findAll() {
        return GenericService.findAll(repository);
    }

    public Caracteristica findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Caracteristica create(final Caracteristica dato) {
        Caracteristica c = new Caracteristica();
        c.setNombre(dato.getNombre());
        return GenericService.save(repository, c);
    }

}
