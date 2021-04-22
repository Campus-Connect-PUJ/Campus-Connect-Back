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
        return repository.findAll();
    }

    public Carrera findById(Long id) {
        return repository.findById(id).get();
    }

    public Carrera create(final Carrera dato, final Long id) {
        Carrera c = new Carrera();
        Facultad f = fService.findById(id);
        c.setNombre(dato.getNombre());
        c.setFacultad(f);
        return repository.save(c);
    }

    public Carrera agregarCarrera(long idCar, UsuarioGeneral ug) {
        Carrera c = this.findById(idCar);
        ug.agregarCarrera(c);

        return repository.save(c);
    }

}
