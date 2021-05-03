package CampusConnect.CCBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Eventos;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Service.CaracteristicasService;

@RestController
@RequestMapping("/caracteristica")
class CaracteristicasController {
    @Autowired
    private CaracteristicasService cService;

    @GetMapping("all")
    public Iterable<Caracteristica> findAll() {
        return cService.findAll();
    }

    @GetMapping("{id}")
    public Caracteristica findById(@PathVariable("id") final Long id) {
        return cService.findById(id);
    }

    @GetMapping("{id}/eventos")
    public List<Eventos> getEventos(@PathVariable("id") final Long id) {
        return cService.findById(id).getEventos();
    }

    @GetMapping("{id}/tematicas")
    public List<Tematica> getTematicas(@PathVariable("id") final Long id) {
        return cService.findById(id).getTematicas();
    }

    @PostMapping
    public Caracteristica create(@RequestBody final Caracteristica dato) {
        return cService.create(dato);
    }

}
