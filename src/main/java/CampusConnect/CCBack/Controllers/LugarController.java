package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Service.LugarService;

@RestController
@RequestMapping("/lugar")
class LugarController {
    @Autowired
    private LugarService lService;

    @GetMapping("all")
    public Iterable<Lugar> findAll() {
        return lService.findAll();
    }

    @GetMapping("{id}")
    public Lugar findById(@PathVariable("id") final Long id) {
        return lService.findById(id);
    }

    @PostMapping
    public Lugar create(@RequestBody final Lugar dato) {
        return lService.create(dato);
    }

}
