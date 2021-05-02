package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Service.AsignaturaService;

@RestController
@RequestMapping("/asignatura")
class AsignaturaController {

    @Autowired
    private AsignaturaService aService;

    @GetMapping("all")
    public Iterable<Asignatura> findAll() {
        return aService.findAll();
    }

    @GetMapping("{id}")
    public Asignatura findById(@PathVariable("id") final Long id) {
        return aService.findById(id);
    }

    @PostMapping
    public Asignatura create(@RequestBody final Asignatura dato) {
        return aService.create(dato);
    }

}
