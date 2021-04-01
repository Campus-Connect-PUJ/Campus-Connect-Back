package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Repository.TipoComidaRepository;

@RestController
@RequestMapping("/tipo_comida")
class TipoComidaService {
    @Autowired
    private TipoComidaRepository repository;

    @GetMapping("all")
    public Iterable<TipoComida> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public TipoComida findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public TipoComida create(@RequestBody final TipoComida dato) {
        TipoComida c = new TipoComida();
        c.setTipo(dato.getTipo());
        return repository.save(c);
    }

}
