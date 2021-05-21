package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Repository.LugarRepository;

@Service
public class LugarService {

    @Autowired
    private LugarRepository repository;

    @GetMapping("all")
    public Iterable<Lugar> findAll() {
        return GenericService.findAll(repository);
    }

    @GetMapping("{id}")
    public Lugar findById(final Long id) {
        return GenericService.findById(repository, id);
    }

    @PostMapping
    public Lugar create(final Lugar dato) {
        Lugar c = new Lugar();
        c.setNombre(dato.getNombre());
        c.setUbicacion(dato.getUbicacion());
        return GenericService.create(repository, c);
    }

}
