package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        return repository.findAll();
    }

    public RespuestaForo findById(final Long id) {
        return repository.findById(id).get();
    }

    public List<RespuestaForo> findRespuestasUsuario(Long id){
        List<RespuestaForo> respuestasUsuario = new ArrayList<>();
        List<RespuestaForo> respuestasTotales = new ArrayList<>();

        respuestasTotales = (List<RespuestaForo>) repository.findAll();
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
        return repository.save(respuesta);
    }

    public RespuestaForo restarVotoAForo(final Long idRespuesta){
        RespuestaForo respuesta = this.findById(idRespuesta);
        respuesta.dislike();
        return repository.save(respuesta);
    }

    public void borrarRespuestaForo(Long idRespuesta, String email){
        UsuarioGeneral ug = uService.findByEmail(email);
        List<RespuestaForo> respuestasUsuario = ug.getRespuestasForo();
        RespuestaForo respuesta = repository.findById(idRespuesta).get();
        
        if(respuestasUsuario.contains(respuesta)){
            respuestasUsuario.remove(respuesta);
            ug.setRespuestasForo(respuestasUsuario);
        }

        repository.delete(respuesta);
        uService.guardarUsuario(ug);
    }


    
}
