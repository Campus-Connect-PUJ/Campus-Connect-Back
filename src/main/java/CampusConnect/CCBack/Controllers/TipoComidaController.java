package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Service.TipoComidaService;

@RestController
@RequestMapping("/tipo_comida")
class TipoComidaController {

    @Autowired
    private TipoComidaService repository;

    @GetMapping("all")
    public Iterable<TipoComida> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public TipoComida findById(@PathVariable("id") final Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public TipoComida create(@RequestBody final TipoComida dato) {
        return repository.create(dato);
    }

}
