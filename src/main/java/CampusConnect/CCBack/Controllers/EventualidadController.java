package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Eventualidad;
import CampusConnect.CCBack.Service.EventualidadService;
import CampusConnect.CCBack.Wrappers.WrapperUbicacion;

@RestController
@RequestMapping("/eventualidad")
class EventualidadController {

    private static final int LIMITE_EVENTUALIDADES = 100;

    @Autowired
    private EventualidadService eService;

    @GetMapping("all")
    public Iterable<Eventualidad> findAll() {
        return eService.findAll();
    }

    @GetMapping("{id}")
    public Eventualidad findById(@PathVariable("id") final Long id) {
        return eService.findById(id);
    }

    @PostMapping
    public Eventualidad create(@RequestBody final Eventualidad dato) {
        return eService.create(dato);
    }

    @PostMapping("cercanas")
    public Iterable<Eventualidad> getCercano(@RequestBody final WrapperUbicacion ubicacion) {
        return eService.findCercano(
            ubicacion.getLatitud(),
            ubicacion.getLongitud(),
            LIMITE_EVENTUALIDADES
            );
    }

}
