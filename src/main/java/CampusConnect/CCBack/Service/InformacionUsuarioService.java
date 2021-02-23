package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.InformacionUsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InformacionUsuarioService {
    @Autowired
    private InformacionUsuarioRepository repository;

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("/usuarios")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioGeneral findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }
}
