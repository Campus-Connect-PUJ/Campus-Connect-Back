package CampusConnect.CCBack.Service;

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
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.CaracteristicaRepository;

@RestController
@RequestMapping("/caracteristica")
class CaracteristicasService {
    @Autowired
    private CaracteristicaRepository repository;

    @GetMapping("all")
    public Iterable<Caracteristica> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Caracteristica findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/usuarios")
    public List<UsuarioGeneral> getUsuarios(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getUsuarios();
    }

    @GetMapping("{id}/eventos")
    public List<Eventos> getEventos(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getEventos();
    }

    @GetMapping("{id}/tematicas")
    public List<Tematica> getTematicas(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getTematicas();
    }

    @PostMapping
    public Caracteristica create(@RequestBody final Caracteristica dato) {
        Caracteristica c = new Caracteristica();
        c.setNombre(dato.getNombre());
        return repository.save(c);
    }

}
