package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Service.RequisitoService;

@RestController
@RequestMapping("/requisito")
class RequisitoController {

    @Autowired
    private RequisitoService rService;

    @GetMapping("all")
    public Iterable<Requisito> findAll() {
        return rService.findAll();
    }

    @GetMapping("{id}")
    public Requisito findById(@PathVariable("id") final Long id) {
        return rService.findById(id);
    }

    @PostMapping
    public Requisito create(@RequestBody final Requisito dato) {
        return rService.create(dato);
    }

}
