package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;


@Service
public class RespuestaForoService {

    @Autowired
    private RespuestaForoRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    public Iterable<RespuestaForo> findAll() {
        return GenericService.findAll(repository);
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

    public RespuestaForo sumarVotoAForo(final Long idRespuesta){
        RespuestaForo respuesta = this.findById(idRespuesta);
        respuesta.like();
        return GenericService.save(repository, respuesta);
    }

    public RespuestaForo restarVotoAForo(final Long idRespuesta){
        RespuestaForo respuesta = this.findById(idRespuesta);
        respuesta.dislike();
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

}
