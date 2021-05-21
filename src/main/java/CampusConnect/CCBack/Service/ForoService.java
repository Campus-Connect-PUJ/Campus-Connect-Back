package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ForoRepository;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;

@Service
public class ForoService {

    @Autowired
    private ForoRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    @Autowired
    private RespuestaForoService rfService;

    public Iterable<Foro> findAll() {
        return GenericService.findAll(repository);
    }

    public Foro findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    public Foro create(final Foro foroData, String email) {
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
                this.rfService.deleteById(foro.getRespuestas().get(i).getId());
                ugBorrar.borrarRespuestaForo(foro.getRespuestas().get(i));
                uService.guardarUsuario(ugBorrar);
            }

        }

        GenericService.delete(repository, foro);
        uService.guardarUsuario(ug);

    }

    public Foro AgregarRespuestaForo(
        final WrapperRespuestaForo respuesta,
        final Long idForo
    ){
        Foro foro = this.findById(idForo);
        UsuarioGeneral usuarioRespuesta = uService.findById(respuesta.getIdUsuario());
        RespuestaForo nuevaRespuesta = rfService.create(respuesta, foro, usuarioRespuesta);

        foro.agregarRespuesta(nuevaRespuesta);

        return GenericService.save(repository, foro);
    }

    public Foro sumarVotoAForo(
        String email,
        final Long idForo
    ){
        Foro foro = this.findById(idForo);
        UsuarioGeneral ug = uService.findByEmail(email);

        
        if(foro.getUsuariosNoGustaron().contains(ug)){
            foro.quitarUsuarioNoGustaron(ug);
            foro.like();
            return GenericService.save(repository, foro);
        }
        if(!foro.getUsuariosGustaron().contains(ug)){
            foro.like();
            foro.agregarUsuarioGustaron(ug);
        }

        return GenericService.save(repository, foro);
    }

    public Foro restarVotoAForo(
        String email,
        final Long idForo
    ){
        Foro foro = this.findById(idForo);
        UsuarioGeneral ug = uService.findByEmail(email);

        if(foro.getUsuariosGustaron().contains(ug)){
            foro.quitarUsuarioGustaron(ug);
            foro.dislike();
            return GenericService.save(repository, foro);
        }
        if(!foro.getUsuariosNoGustaron().contains(ug)){
            foro.dislike();
            foro.agregarUsuarioNoGustaron(ug);
        }

        return GenericService.save(repository, foro);
    }

}
