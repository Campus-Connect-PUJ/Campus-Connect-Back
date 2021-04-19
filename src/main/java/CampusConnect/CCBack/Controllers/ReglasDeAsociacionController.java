package CampusConnect.CCBack.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.ReglaAsociacionConPuntaje;
import CampusConnect.CCBack.Model.ReglasDeAsociacion;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ReglasDeAsociacionRepository;
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

    @GetMapping("usuario/{id}")
    public Tip obtenerRecomendacionTip(@PathVariable("id") final Long id){
        return repository.obtenerRecomendacionTip(id);
    }

}