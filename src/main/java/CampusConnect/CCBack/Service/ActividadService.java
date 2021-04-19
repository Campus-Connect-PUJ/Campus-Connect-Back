package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Repository.ActividadRepository;

@Service
class ActividadService {

    @Autowired
    private ActividadRepository repository;

    public Iterable<Actividad> findAll() {
        return repository.findAll();
    }

    public Actividad findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    public Actividad findByName(String name) {
        return repository.findByNombre(name);
    }

    public Actividad crear(String name){
        Actividad actividad = new Actividad();
        actividad.setNombre(name);

        return repository.save(actividad);
    }

}
