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
        return GenericService.findAll(repository);
    }

    public Foro findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Foro crearForo(final Foro foroData, String email) {
        Foro foro = new Foro();
        UsuarioGeneral ug = uService.findByEmail(email);
        // no es necesario poner las demas variables, ya que el
        // constructor se encarga, ademas un post al ser creado
        // siempre tendra una lista vacia de respuestas, la fecha
        // puede ser sacada de forma local y no sera reportado
        foro.setDescripcion(foroData.getDescripcion());
        foro.setTitulo(foroData.getTitulo());
        foro.setUsuario(ug);
        ug.agregarForo(foro);

        uService.guardarUsuario(ug);
        return GenericService.save(repository, foro);
    }

    public void borrarForo(Long idForo, String email){
        UsuarioGeneral ug = uService.findByEmail(email);
        List<Foro> forosUsuario = ug.getForos();
        Foro foro = GenericService.findById(repository, idForo);
        
        if(forosUsuario.contains(foro)){
            forosUsuario.remove(foro);
            ug.setForos(forosUsuario);
            
            for(int i=0; i<foro.getRespuestas().size(); i++){
                UsuarioGeneral ugBorrar = foro.getRespuestas().get(i).getUsuario();
                respuestaRepository.deleteById(foro.getRespuestas().get(i).getId());
                ugBorrar.borrarRespuestaForo(foro.getRespuestas().get(i));
                uService.guardarUsuario(ugBorrar);
            }

        }

        GenericService.delete(repository, foro);
        uService.guardarUsuario(ug);

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

        GenericService.save(repository, foro);
        respuestaRepository.save(nuevaRespuesta);

    }

    public Foro sumarVotoAForo(final Long idForo){
        Foro foro = this.findById(idForo);
        foro.like();
        return GenericService.save(repository, foro);
    }

    public Foro restarVotoAForo(final Long idForo){
        Foro foro = this.findById(idForo);
        foro.dislike();
        return GenericService.save(repository, foro);
    }

}
