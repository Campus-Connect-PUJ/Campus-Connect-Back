package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ForoRepository;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;

import java.util.ArrayList;
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
@RequestMapping("/respuestaForo")
class RespuestaForoService {
    @Autowired
    private RespuestaForoRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    @GetMapping("all")
    public Iterable<RespuestaForo> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public RespuestaForo findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @PutMapping("sumar/{id}")
    public RespuestaForo sumarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        RespuestaForo respuesta = this.findById(idRespuesta);
        respuesta.like();
        return repository.save(respuesta);
    }

    @PutMapping("restar/{id}")
    public RespuestaForo restarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        RespuestaForo respuesta = this.findById(idRespuesta);
        respuesta.dislike();
        return repository.save(respuesta);
    }
    
}
