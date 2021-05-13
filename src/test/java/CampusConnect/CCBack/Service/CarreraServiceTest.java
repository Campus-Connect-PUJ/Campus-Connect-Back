package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.CarreraRepository;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository repository;

    @Autowired
    private FacultadesService fService;

    public Iterable<Carrera> findAll() {
        return GenericService.findAll(repository);
    }

    public Carrera findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public Carrera create(final Carrera dato, final Long id) {
        Carrera c = new Carrera();
        Facultad f = fService.findById(id);
        c.setNombre(dato.getNombre());
        c.setFacultad(f);
        return GenericService.create(repository, c);
    }

    public Carrera agregarCarrera(long idCar, UsuarioGeneral ug) {
        Carrera c = this.findById(idCar);
        ug.agregarCarrera(c);

        return GenericService.save(repository, c);
    }

}
