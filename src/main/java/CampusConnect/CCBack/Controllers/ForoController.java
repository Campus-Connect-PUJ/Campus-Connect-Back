package CampusConnect.CCBack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Service.ForoService;
import CampusConnect.CCBack.Service.RespuestaForoService;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;

@RestController
@RequestMapping("/foro")
class ForoController {

    @Autowired
    private ForoService fService;

    @Autowired
    private RespuestaForoService rfService;

    @GetMapping("all")
    public Iterable<Foro> findAll() {
        return fService.findAll();
    }

    @GetMapping("{id}")
    public Foro findById(@PathVariable("id") final Long id) {
        return fService.findById(id);
    }

    @GetMapping("{id}/respuestas")
    public List<RespuestaForo> findRespuestasById(@PathVariable("id") final Long id) {
        return fService.findById(id).getRespuestas();
    }

    @PostMapping
    public Foro crearForo(
        @RequestBody final Foro foroData,
        @AuthenticationPrincipal String email
    ) {
        return fService.crearForo(foroData, email);
    }

    @PutMapping("/borrarForo/{id_foro}")
    public void borrarForo(
        @PathVariable("id_foro") final Long idForo,
        @AuthenticationPrincipal String email
    ){
        fService.borrarForo(idForo, email);
    }  

    @PostMapping("{id}/respuesta")
    public void AgregarRespuestaForo(
        @RequestBody final WrapperRespuestaForo respuesta,
        @PathVariable("id") final Long idForo
    ){
        fService.AgregarRespuestaForo(respuesta, idForo);
    }

    @PutMapping("borrarRespuesta/{idRespuesta}")
    public void borrarRespuestaForo(
        @PathVariable("idRespuesta") final Long idRespuesta,
        @AuthenticationPrincipal String email
    ){
        rfService.borrarRespuestaForo(idRespuesta, email);
    }

    @PutMapping("sumar/{id}")
    public Foro sumarVotoAForo(@PathVariable("id") final Long idForo){
        return fService.sumarVotoAForo(idForo);
    }

    @PutMapping("restar/{id}")
    public Foro restarVotoAForo(@PathVariable("id") final Long idForo){
        return fService.restarVotoAForo(idForo);
    }

}
