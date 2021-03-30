package CampusConnect.CCBack.Service;

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
import CampusConnect.CCBack.Repository.TipoAprendizajeRepository;

@RestController
@RequestMapping("/tipo_aprendizaje")
class TipoAprendizajeService {

    @Autowired
    private TipoAprendizajeRepository repository;

    @GetMapping("all")
    public Iterable<TipoAprendizaje> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public TipoAprendizaje findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/usuarios")
    public List<UsuarioGeneral> conseguirUsuarios(@PathVariable("id") Long id) {
        return repository.findById(id).get().getUsuarios();
    }

    @GetMapping("{id}/tip")
    public List<Tip> conseguirTips(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTips();
    }

    @PostMapping
    public TipoAprendizaje create(@RequestBody final TipoAprendizaje dato) {
        TipoAprendizaje c = new TipoAprendizaje();
        c.setDescripcion(dato.getDescripcion());
        return repository.save(c);
    }

}
