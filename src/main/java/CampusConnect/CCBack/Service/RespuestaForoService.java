package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;

@Service
public class RespuestaForoService {

    @Autowired
    private RespuestaForoRepository repository;

    public Iterable<RespuestaForo> findAll() {
        return repository.findAll();
    }

    public RespuestaForo findById(final Long id) {
        return repository.findById(id).get();
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
    
}
