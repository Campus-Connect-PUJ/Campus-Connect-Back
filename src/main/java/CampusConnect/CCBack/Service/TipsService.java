package CampusConnect.CCBack.Service;

import java.util.List;

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
        return GenericService.findAll(repository);
    }

    public Tip findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public Tip crear(final WrapperTip data) {
        Tip tip = new Tip();
        UsuarioGeneral ug = ugService.findById(data.getIdUsuario());
        tip.setDescripcion(data.getTip().getDescripcion());
        tip.setUsuario(ug);
        tip.setNivelExigencia(data.getExigencia());
        tip.setIdUsuarioCreador(ug.getId());

        for(Long id: data.getTiposAprendizaje()) {
            TipoAprendizaje c = taService.findById(id);
            tip.agregaTipoAprendizaje(c);
        }

        return GenericService.create(repository, tip);
    }

    public void borrarTip(Long idTip, String email){
        UsuarioGeneral ug = ugService.findByEmail(email);
        List<Tip> tipsUsuario = ug.getTips();
        Tip tip = this.findById(idTip);
        
        if(tipsUsuario.contains(tip)){
            tipsUsuario.remove(tip);
            ug.setTips(tipsUsuario);
        }

        GenericService.delete(repository, tip);
        ugService.guardarUsuario(ug);


    }


    public UsuarioGeneral agregarTipGustado(
        String email,
        final Long idTip
    ){
        Tip tip = this.findById(idTip);
        
        UsuarioGeneral ug = ugService.findByEmail(email);

        if(ug.getTipsNoGustados().contains(tip)){
            tip.quitarUsuarioNoGustaron(ug);
            ug.quitarUsuarioNoGustaron(tip);
            tip.like();
            GenericService.save(repository, tip);
            return ugRepository.save(ug);
        }

        if(!ug.getTipsGustados().contains(tip)){
            tip.like();
            tip.agregarUsuarioGustaron(ug);
            ug.agregarTipGustaron(tip);
            GenericService.save(repository, tip);
        }
        

        return ugRepository.save(ug);
    }

    public UsuarioGeneral agregarTipNoGustado(
        String email,
        final Long idTip
    ){
        Tip tip = this.findById(idTip);
        
        UsuarioGeneral ug = ugService.findByEmail(email);

        if(ug.getTipsGustados().contains(tip)){
            tip.quitarUsuarioGustaron(ug);
            ug.quitarUsuarioGustaron(tip);
            tip.dislike();
            GenericService.save(repository, tip);
            return ugRepository.save(ug);
        }

        if(!ug.getTipsNoGustados().contains(tip)){
            tip.dislike();
            tip.agregarUsuarioNoGustaron(ug);
            ug.agregarTipNoGustaron(tip);
            GenericService.save(repository, tip);
        }
         
        return ugRepository.save(ug);
    }

    public Tip sumarVotoAForo(
        final Long idTip
    ){
        Tip tip = this.findById(idTip);
        tip.like();
        return GenericService.save(repository, tip);
    }

    public Tip restarVotoAForo(
        final Long idForo
    ){
        Tip tip = this.findById(idForo);
        tip.dislike();
        return GenericService.save(repository, tip);
    }


}
