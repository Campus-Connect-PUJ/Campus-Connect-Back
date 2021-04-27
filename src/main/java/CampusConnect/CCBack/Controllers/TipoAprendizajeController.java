package CampusConnect.CCBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Service.TipoAprendizajeService;

@RestController
@RequestMapping("/tipo_aprendizaje")
class TipoAprendizajeController {

    @Autowired
    private TipoAprendizajeService taService;

    @GetMapping("all")
    public Iterable<TipoAprendizaje> findAll() {
        return taService.findAll();
    }

    @GetMapping("{id}")
    public TipoAprendizaje findById(@PathVariable("id") Long id) {
        return taService.findById(id);
    }

    @GetMapping("{id}/usuarios")
    public List<UsuarioGeneral> conseguirUsuarios(@PathVariable("id") Long id) {
        return taService.findById(id).getUsuarios();
    }

    @GetMapping("{id}/tip")
    public List<Tip> conseguirTips(@PathVariable("id") Long id) {
        return taService.findById(id).getTips();
    }

    @PostMapping
    public TipoAprendizaje create(@RequestBody final TipoAprendizaje dato) {
        return taService.create(dato);
    }

}
