package CampusConnect.CCBack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Service.RespuestaForoService;

@RestController
@RequestMapping("/respuestaForo")
class RespuestaForoController {

    @Autowired
    private RespuestaForoService repository;

    @GetMapping("all")
    public Iterable<RespuestaForo> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public RespuestaForo findById(@PathVariable("id") final Long id) {
        return repository.findById(id);
    }

    @GetMapping("usuario/{id}")
    public Iterable<RespuestaForo> findRespuestasUsuario(
        @PathVariable("id") final Long id
    ) {
        return repository.findRespuestasUsuario(id);
    }

    @PutMapping("sumar/{id}")
    public RespuestaForo sumarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        return repository.sumarVotoAForo(idRespuesta);
    }

    @PutMapping("restar/{id}")
    public RespuestaForo restarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        return repository.restarVotoAForo(idRespuesta);
    }
    
}
