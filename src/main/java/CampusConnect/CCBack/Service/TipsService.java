package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.TipRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Wrappers.WrapperTip;

@Service
public class TipsService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private UsuarioGeneralRepository ugRepository;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private TipoAprendizajeService taService;

    public Iterable<Tip> findAll() {
        return repository.findAll();
    }

    public Tip findById(Long id) {
        return repository.findById(id).get();
    }

    public Tip crear(final WrapperTip data) {
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

    public UsuarioGeneral agregarTipGustado(
        final Long idUsuario,
        final Long idTip
    ){
        System.out.println("Entra a gustar");
        Tip tip = this.findById(idTip);
        tip.like();
        UsuarioGeneral ug = ugService.findById(idUsuario);

        if(!ug.getTipsGustados().contains(tip)){
            tip.agregarUsuarioGustaron(ug);
            ug.agregarTipGustaron(tip);
            repository.save(tip);
        }



        System.out.println("Sale a gustar");
        return ugRepository.save(ug);
    }

    public UsuarioGeneral agregarTipNoGustado(
        final Long idUsuario,
        final Long idTip
    ){
        System.out.println("entra");
        Tip tip = this.findById(idTip);
        tip.dislike();
        UsuarioGeneral ug = ugService.findById(idUsuario);
        if(!ug.getTipsNoGustados().contains(tip)){
            tip.agregarUsuarioNoGustaron(ug);
            ug.agregarTipNoGustaron(tip);
            repository.save(tip);
        }
         
        return ugRepository.save(ug);
    }

    public Tip sumarVotoAForo(
        final Long idTip
    ){
        Tip tip = this.findById(idTip);
        tip.like();
        return repository.save(tip);
    }

    public Tip restarVotoAForo(
        final Long idForo
    ){
        Tip tip = this.findById(idForo);
        tip.dislike();
        return repository.save(tip);
    }


}
