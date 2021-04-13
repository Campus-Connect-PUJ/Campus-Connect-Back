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
@RequestMapping("/foro")
class ForoService {
    @Autowired
    private ForoRepository repository;

    @Autowired
    private RespuestaForoRepository respuestaRepository;

    @Autowired
    private UsuarioGeneralService uService;

    @GetMapping("all")
    public Iterable<Foro> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Foro findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/respuestas")
    public List<RespuestaForo> findRespuestasById(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getRespuestas();
    }

    @PostMapping("{id}")
    public Foro crearForo(
        @RequestBody final Foro foroData,
        @PathVariable("id") final Long idUsuario
        ) {
        Foro foro = new Foro();
        UsuarioGeneral ug = uService.findById(idUsuario);
        // no es necesario poner las demas variables, ya que el
        // constructor se encarga, ademas un post al ser creado
        // siempre tendra una lista vacia de respuestas, la fecha
        // puede ser sacada de forma local y no sera reportado
        foro.setDescripcion(foroData.getDescripcion());
        foro.setTitulo(foroData.getTitulo());
        foro.setUsuario(ug);

        return repository.save(foro);
    }

    @PostMapping("{id}/respuesta")
    public void AgregarRespuestaForo(
        @RequestBody final WrapperRespuestaForo respuesta,
        @PathVariable("id") final Long idForo
    ){
        RespuestaForo nuevaRespuesta = new RespuestaForo();
        Foro foro = this.findById(idForo);
        UsuarioGeneral usuarioRespuesta = uService.findById(respuesta.getIdUsuario());

        nuevaRespuesta.setTexto(respuesta.getTexto());
        nuevaRespuesta.setForo(foro);
        nuevaRespuesta.setUsuario(usuarioRespuesta);
        foro.agregarRespuesta(nuevaRespuesta);

        repository.save(foro);
        respuestaRepository.save(nuevaRespuesta);

        System.out.println("RespuestaForo "+ nuevaRespuesta.getUsuario().getNombre()+ " "+ nuevaRespuesta.getTexto() + " "+ foro.getRespuestas().size());
    }

    @PutMapping("sumar/{id}")
    public Foro sumarVotoAForo(
        @PathVariable("id") final Long idForo
    ){
        Foro foro = this.findById(idForo);
        foro.like();
        return repository.save(foro);
    }

    @PutMapping("restar/{id}")
    public Foro restarVotoAForo(
        @PathVariable("id") final Long idForo
    ){
        Foro foro = this.findById(idForo);
        foro.dislike();
        return repository.save(foro);
    }


    
}
