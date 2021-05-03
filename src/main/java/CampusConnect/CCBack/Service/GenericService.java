package CampusConnect.CCBack.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// estas funciones lo unico que buscan hacer, es "encapsular" las
// funciones comunes que se comuniquen directamente con el
// repositorio, de forma que se pueda especificar las excepciones que
// estas deban lanzar

public class GenericService {

    public static <E> Iterable<E> findAll(CrudRepository<E, Long> repository) {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se han encontrado los datos buscados", e);
        }
    }

    public static <E> E findById(CrudRepository<E, Long> repository, Long id) {
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto con id " + id + " no encontrado", e);
        }
    }

    public static <E> E save(CrudRepository<E, Long> repository, E obj) {
        try {
            return repository.save(obj);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error guardando objeto", e);
        }
    }

    public static <E> E create(CrudRepository<E, Long> repository, E obj) {
        try {
            return repository.save(obj);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error creando objeto", e);
        }
    }

    public static <E> void delete(CrudRepository<E, Long> repository, E obj) {
        try {
            repository.delete(obj);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error eliminando objeto", e);
        }
    }

}
