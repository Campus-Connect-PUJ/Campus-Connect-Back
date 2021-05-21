package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Repository.UsuarioMonitorRepository;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@Service
class UsuarioMonitorService {

    @Autowired
    private UsuarioMonitorRepository repository;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private AsignaturaService aService;

    public Iterable<UsuarioMonitor> findAllForos() {
        return GenericService.findAll(repository);
    }

    public UsuarioMonitor findById(@PathVariable("id") Long id) {
        return GenericService.findById(repository, id);
    }

    public UsuarioMonitor create(
        String email,
        @PathVariable("id") Long id
        ) {

        UsuarioGeneral ug = ugService.findByEmail(email);
        UsuarioMonitor um = new UsuarioMonitor();
        Asignatura a = aService.findById(id);
        um.setAsignatura(a);
        um.setUsuario(ug);
        return GenericService.create(repository, um);
    }

    public UsuarioMonitor guardar(UsuarioMonitor um) {
        return GenericService.save(repository, um);
    }

    public void borrar(UsuarioMonitor um){
        GenericService.delete(repository, um);
    }

}
