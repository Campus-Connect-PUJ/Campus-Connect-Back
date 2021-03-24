package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.TipRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TipsService {
    @Autowired
    private TipRepository repository;

    @GetMapping("/tips")
    public Iterable<Tip> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/tip/{id}")
    public Tip findTipById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/tip/{id}/tipos_aprendizaje")
    public List<TipoAprendizaje> conseguirTiposAprendizajeTip(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposAprendizaje();
    }

    @GetMapping("/tip/{id}/usuarios_gustaron")
    public List<UsuarioGeneral> conseguirUsuariosGustaronTip(@PathVariable("id") Long id) {
        return repository.findById(id).get().getUsuariosGustaron();
    }
}
