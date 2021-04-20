package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Service.CarreraService;

@RestController
@RequestMapping("/carrera")
class CarreraController {

    @Autowired
    private CarreraService cService;

    @GetMapping("all")
    public Iterable<Carrera> findAll() {
        return cService.findAll();
    }

    @GetMapping("{id}")
    public Carrera findById(@PathVariable("id") Long id) {
        return cService.findById(id);
    }

    @PostMapping("{id}")
    public Carrera create(
        @RequestBody final Carrera dato,
        @PathVariable("id") final Long id) {
        return cService.create(dato, id);
    }

    public Carrera agregarCarrera(long idCar, UsuarioGeneral ug) {
        return cService.agregarCarrera(idCar, ug);
    }

}
