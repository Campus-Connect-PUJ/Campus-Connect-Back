package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Service.ReglasDeAsociacionService;
import CampusConnect.CCBack.Wrappers.WrapperReglaAsociacion;

@RestController
@RequestMapping("/reglas")
class ReglasDeAsociacionController {

    @Autowired
    private ReglasDeAsociacionService repository;

    @GetMapping("all")
    public Iterable<ReglasDeAsociacion> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ReglasDeAsociacion findById(@PathVariable("id") final Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public ReglasDeAsociacion crearReglaDeAsociacion(
        @RequestBody final WrapperReglaAsociacion reglaData) {
        return repository.crearReglaDeAsociacion(reglaData);
    }

    @GetMapping("usuario")
    public Tip obtenerRecomendacionTip(
        @AuthenticationPrincipal String email
        //@PathVariable final long id
        ){

        return repository.obtenerRecomendacionTip(email);
    }

    @DeleteMapping("borrar")
    public Long borrarReglas(){
        return repository.borrarReglas();
    }

}
