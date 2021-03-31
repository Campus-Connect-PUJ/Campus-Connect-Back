package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ForoRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ForoService {
    @Autowired
    private ForoRepository repository;

    @Autowired
    private UsuarioGeneralRepository usuarioRepo;

    @GetMapping("/foros")
    public Iterable<Foro> findAll() {
        return repository.findAll();
    }

    @GetMapping("/foro/{id}")
    public Foro findById(@PathVariable("id") final Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/foro/{id}/respuestas")
    public List<RespuestaForo> findRespuestasById(@PathVariable("id") final Long id) {
        return repository.findById(id).get().getRespuestas();
    }

    @PostMapping("/foro/{id}")
    public Foro crearForo(
        @RequestBody final Foro foroData,
        @PathVariable("id") final Long idUsuario
        ) {
        Foro foro = new Foro();
        UsuarioGeneral ug = usuarioRepo.findById(idUsuario).get();
        // no es necesario poner las demas variables, ya que el
        // constructor se encarga, ademas un post al ser creado
        // siempre tendra una lista vacia de respuestas, la fecha
        // puede ser sacada de forma local y no sera reportado
        foro.setDescripcion(foroData.getDescripcion());
        foro.setTitulo(foroData.getTitulo());
        foro.setUsuario(ug);

        return repository.save(foro);
    }
}
