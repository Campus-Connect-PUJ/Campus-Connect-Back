package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Repository.TipRepository;
import CampusConnect.CCBack.Wrappers.WrapperTip;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip")
class TipsService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private UsuarioGeneralRepository usuarioRepo;

    @Autowired
    private TipoAprendizajeService taService;

    @GetMapping("all")
    public Iterable<Tip> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Tip findTipById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/tipos_aprendizaje")
    public List<TipoAprendizaje> conseguirTiposAprendizajeTip(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTiposAprendizaje();
    }

    @GetMapping("{id}/usuarios_gustaron")
    public List<UsuarioGeneral> conseguirUsuariosGustaronTip(@PathVariable("id") Long id) {
        return repository.findById(id).get().getUsuariosGustaron();
    }

    @PostMapping
    public Tip crear(
        @RequestBody final WrapperTip data
    ) {
        Tip tip = new Tip();
        UsuarioGeneral ug = usuarioRepo.findById(data.getIdUsuario()).get();
        tip.setDescripcion(data.getTip().getDescripcion());
        tip.setUsuario(ug);

        for(int i = 0; i<data.getTiposAprendizaje().size(); i++){
            long id = data.getTiposAprendizaje().get(i);
            TipoAprendizaje c = taService.findById(id);
            tip.agregaTipoAprendizaje(c);
        }

        return repository.save(tip);
    }
}
