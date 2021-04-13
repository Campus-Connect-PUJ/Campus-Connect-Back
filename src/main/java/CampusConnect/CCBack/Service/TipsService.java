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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip")
class TipsService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private UsuarioGeneralRepository ugRepository;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private TipoAprendizajeService taService;

    @GetMapping("all")
    public Iterable<Tip> findAllTips() {
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
        UsuarioGeneral ug = ugService.findById(data.getIdUsuario());
        tip.setDescripcion(data.getTip().getDescripcion());
        tip.setUsuario(ug);

        for(Long id: data.getTiposAprendizaje()) {
            TipoAprendizaje c = taService.findById(id);
            tip.agregaTipoAprendizaje(c);
        }

        return repository.save(tip);
    }

    @PutMapping("tipsGustados/{idUsuario}/{idTip}")
    public UsuarioGeneral agregarTipGustado(
        @PathVariable("idUsuario") final Long idUsuario,
        @PathVariable("idTip") final Long idTip
    ){
        System.out.println("Entra a gustar");
        Tip tip = this.findTipById(idTip);
        tip.like();
        UsuarioGeneral ug = ugService.findById(idUsuario);

        tip.agregarUsuarioGustaron(ug);
        ug.agregarTipGustaron(tip);

        repository.save(tip);
        System.out.println("Sale a gustar");
        return ugRepository.save(ug);
    }

    @PutMapping("tipsNoGustados/{idUsuario}/{idTip}")
    public UsuarioGeneral agregarTipNoGustado(
        @PathVariable("idUsuario") final Long idUsuario,
        @PathVariable("idTip") final Long idTip
    ){
        System.out.println("entra");
        Tip tip = this.findTipById(idTip);
        tip.dislike();
        UsuarioGeneral ug = ugService.findById(idUsuario);

        tip.agregarUsuarioNoGustaron(ug);
        ug.agregarTipNoGustaron(tip);

        
        repository.save(tip);
        return ugRepository.save(ug);
    }

    @PutMapping("sumar/{id}")
    public Tip sumarVotoAForo(
        @PathVariable("id") final Long idTip
    ){
        Tip tip = this.findTipById(idTip);
        tip.like();
        return repository.save(tip);
    }

    @PutMapping("restar/{id}")
    public Tip restarVotoAForo(
        @PathVariable("id") final Long idForo
    ){
        Tip tip = this.findTipById(idForo);
        tip.dislike();
        return repository.save(tip);
    }


}
