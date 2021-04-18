package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Repository.UsuarioMonitorRepository;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
@RequestMapping("/monitor")
class UsuarioMonitorService {

    @Autowired
    private UsuarioMonitorRepository repository;

    @Autowired
    private AsignaturaService aService;

    @GetMapping("all")
    public Iterable<UsuarioMonitor> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public UsuarioMonitor findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("{id}")
    public UsuarioMonitor create(
        final WrapperUsuarioGeneral data,
        @AuthenticationPrincipal UsuarioGeneral ug,
        @PathVariable("id") Long id
        ) {
        UsuarioMonitor um = new UsuarioMonitor();
        Asignatura a = aService.findById(id);
        um.setAsignatura(a);
        um.setUsuario(ug);
        return repository.save(um);
    }

    // TODO
    // public UsuarioMonitor agregarHorario() {
    // }

}
