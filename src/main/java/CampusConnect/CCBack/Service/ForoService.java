package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ForoRepository;
import CampusConnect.CCBack.Repository.RespuestaForoRepository;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;

@Service
public class ForoService {

    @Autowired
    private ForoRepository repository;

    @Autowired
    private RespuestaForoRepository respuestaRepository;

    @Autowired
    private UsuarioGeneralService uService;

    public Iterable<Foro> findAll() {
        return repository.findAll();
    }

    public Foro findById(final Long id) {
        return repository.findById(id).get();
    }

    public Foro crearForo(final Foro foroData, final Long idUsuario) {
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

    public void AgregarRespuestaForo(
        final WrapperRespuestaForo respuesta,
        final Long idForo
    ){
        RespuestaForo nuevaRespuesta = new RespuestaForo();
        Foro foro = this.findById(idForo);
        UsuarioGeneral usuarioRespuesta = uService.findById(respuesta.getIdUsuario());

        nuevaRespuesta.setTexto(respuesta.getTexto());
        nuevaRespuesta.setForo(foro);
        nuevaRespuesta.setIdForoRespondido(foro.getId());
        nuevaRespuesta.setUsuario(usuarioRespuesta);
        foro.agregarRespuesta(nuevaRespuesta);

        repository.save(foro);
        respuestaRepository.save(nuevaRespuesta);

        System.out.println("RespuestaForo "+ nuevaRespuesta.getUsuario().getNombre()+ " "+ nuevaRespuesta.getTexto() + " "+ foro.getRespuestas().size());
    }

    public Foro sumarVotoAForo(final Long idForo){
        Foro foro = this.findById(idForo);
        foro.like();
        return repository.save(foro);
    }

    public Foro restarVotoAForo(final Long idForo){
        Foro foro = this.findById(idForo);
        foro.dislike();
        return repository.save(foro);
    }

}
