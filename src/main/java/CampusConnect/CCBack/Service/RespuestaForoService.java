package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;


@Service
public class RespuestaForoService {

    @Autowired
    private RespuestaForoRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    public Iterable<RespuestaForo> findAll() {
        return GenericService.findAll(repository);
    }

    public void deleteById(long id) {
        GenericService.delete(repository, this.findById(id));
    }


    public RespuestaForo findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public List<RespuestaForo> findRespuestasUsuario(Long id){
        List<RespuestaForo> respuestasUsuario = new ArrayList<>();
        List<RespuestaForo> respuestasTotales = new ArrayList<>();

        respuestasTotales = (List<RespuestaForo>) this.findAll();
        for(int i=0; i<respuestasTotales.size(); i++){
            if(respuestasTotales.get(i).getUsuario().getId() == id ){
                respuestasUsuario.add(respuestasTotales.get(i));
            }
        }
        return respuestasUsuario;
    }

    public RespuestaForo sumarVotoAForo(
        String email, 
        final Long idRespuesta
    ){
        RespuestaForo respuesta = this.findById(idRespuesta);
        UsuarioGeneral ug = uService.findByEmail(email);

        if(respuesta.getUsuariosNoGustaron().contains(ug)){
            respuesta.quitarUsuarioNoGustaron(ug);
            respuesta.like();
            return GenericService.save(repository, respuesta);
        }
        if(!respuesta.getUsuariosGustaron().contains(ug)){
            respuesta.like();
            respuesta.agregarUsuarioGustaron(ug);
        }

        return GenericService.save(repository, respuesta);
    }

    public RespuestaForo restarVotoAForo(        
        String email, 
        final Long idRespuesta
    ){
        RespuestaForo respuesta = this.findById(idRespuesta);
        UsuarioGeneral ug = uService.findByEmail(email);

        if(respuesta.getUsuariosGustaron().contains(ug)){
            respuesta.quitarUsuarioGustaron(ug);
            respuesta.dislike();
            return GenericService.save(repository, respuesta);
        }
        if(!respuesta.getUsuariosNoGustaron().contains(ug)){
            respuesta.dislike();
            respuesta.agregarUsuarioNoGustaron(ug);
        }
   
        return GenericService.save(repository, respuesta);
    }

    public void borrarRespuestaForo(Long idRespuesta, String email){
        UsuarioGeneral ug = uService.findByEmail(email);
        List<RespuestaForo> respuestasUsuario = ug.getRespuestasForo();
        RespuestaForo respuesta = this.findById(idRespuesta);
        
        if(respuestasUsuario.contains(respuesta)){
            respuestasUsuario.remove(respuesta);
            ug.setRespuestasForo(respuestasUsuario);
        }

        GenericService.delete(repository, respuesta);
        uService.guardarUsuario(ug);
    }

    public RespuestaForo create(
        final WrapperRespuestaForo respuesta,
        final Foro foro,
        final UsuarioGeneral ug
    ){
        RespuestaForo nuevaRespuesta = new RespuestaForo();

        nuevaRespuesta.setTexto(respuesta.getTexto());
        nuevaRespuesta.setForo(foro);
        nuevaRespuesta.setUsuario(ug);

        return GenericService.save(repository, nuevaRespuesta);
    }

}
