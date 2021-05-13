package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.ActividadRepository;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository repository;

    public Iterable<Actividad> findAll() {
        return GenericService.findAll(repository);
    }

    public Actividad findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public Actividad findByName(String name) {
        try {
            return repository.findByNombre(name);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Objeto con nombre " + name + " no encontrado", e);
        }
    }

    public Actividad crear(String name){
        Actividad actividad = new Actividad();
        actividad.setNombre(name);

        return GenericService.save(repository, actividad);
    }

    public Actividad agregarUsuario(Long aid, UsuarioGeneral ug) {

        Actividad a = this.findById(aid);

        a.agregarUsuario(ug);
        return GenericService.save(repository, a);

    }

}
