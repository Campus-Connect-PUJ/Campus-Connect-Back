package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.CarreraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrera")
class CarreraService {
    @Autowired
    private CarreraRepository repository;

    @Autowired
    private FacultadesService fService;

    @GetMapping("all")
    public Iterable<Carrera> findAllCarreras() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Carrera findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("{id}")
    public Carrera create(@RequestBody final Carrera dato,
                          @PathVariable("id") final Long id) {
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
