package CampusConnect.CCBack.Controllers;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ForoRepository;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Service.ForoService;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foro")
class ForoController {

    @Autowired
    private ForoService fService;

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

    @PostMapping("{id}")
    public Foro crearForo(
        @RequestBody final Foro foroData,
        @PathVariable("id") final Long idUsuario
        ) {

            return fService.crearForo(foroData, idUsuario);
    }

    @PutMapping("{idUsuario}/borrarForo/{id_foro}")
    public void borrarForo(
        @PathVariable("id_foro") final Long idForo,
        @PathVariable("idUsuario") final Long idUsuario 
    ){
        fService.borrarForo(idUsuario, idForo);
    }  


    @PostMapping("{id}/respuesta")
    public void AgregarRespuestaForo(
        @RequestBody final WrapperRespuestaForo respuesta,
        @PathVariable("id") final Long idForo
    ){
        fService.AgregarRespuestaForo(respuesta, idForo);
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
