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
    private RespuestaForoService rfService;

    @GetMapping("all")
    public Iterable<RespuestaForo> findAll() {
        return rfService.findAll();
    }

    @GetMapping("{id}")
    public RespuestaForo findById(@PathVariable("id") final Long id) {
        return rfService.findById(id);
    }

    @GetMapping("usuario/{id}")
    public Iterable<RespuestaForo> findRespuestasUsuario(
        @PathVariable("id") final Long id
    ) {
        return rfService.findRespuestasUsuario(id);
    }

    @PutMapping("sumar/{id}")
    public RespuestaForo sumarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        return rfService.sumarVotoAForo(idRespuesta);
    }

    @PutMapping("restar/{id}")
    public RespuestaForo restarVotoAForo(
        @PathVariable("id") final Long idRespuesta
    ){
        return rfService.restarVotoAForo(idRespuesta);
    }

    
    
}
