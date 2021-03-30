package CampusConnect.CCBack.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.InformacionUsuarioRepository;

@RestController
@RequestMapping("/informacion_usuario")
class InformacionUsuarioService {

    @Autowired
    private InformacionUsuarioRepository repository;

    @Autowired
    private UsuarioGeneralService uService;

    @GetMapping("all")
    public Iterable<InformacionUsuario> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public InformacionUsuario findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("{id}")
    public InformacionUsuario create(
        @RequestBody final InformacionUsuario data,
        @PathVariable("id") Long id
        ) {
        InformacionUsuario iu = new InformacionUsuario();
        UsuarioGeneral ug = uService.findById(id);

        iu.setFechaNacimiento(data.getFechaNacimiento());
        iu.setIdentidadGenero(data.getIdentidadGenero());
        iu.setLugarOrigen(data.getLugarOrigen());
        iu.setRaza(data.getRaza());
        iu.setUsuario(ug);

        repository.save(iu);

        ug.setInformacionUsuario(iu);
        return repository.save(iu);
    }
}
